package com.pvn.mvctiles.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pvn.mvctiles.dao.UserDao;
import com.pvn.mvctiles.model.UserDetails;
import com.pvn.mvctiles.model.UserRoleMapping;
import com.pvn.mvctiles.service.UserService;
import com.pvn.mvctiles.vo.UserVO;

@Service
public class UserServiceImpl implements UserService
{

	Logger	OUT	= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao	userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails addUser(UserDetails userDetails)
	{
		userDetails.getSelectedRoles().forEach(roleId -> {
			UserRoleMapping mapping = new UserRoleMapping();
			mapping.setId(roleId);
			mapping.setUserId(userDetails.getId());
			
			userDetails.getRoleList().add(mapping);
		});
		
		String encodedPass = passwordEncoder.encode(userDetails.getPassword());
		OUT.info("Plain Pass: {}, Encoded Pass: {}", userDetails.getPassword(), encodedPass);
		
		userDetails.setPassword(encodedPass);
		userDetails.setEnabled(true);

		return userDao.addUser(userDetails);
	}

	@Override
	public UserDetails modifyUser(UserDetails userDetails)
	{
		String[] arr = userDetails.getPreviousMappings().split(",");
		Map<Integer, UserRoleMapping> roleIdObjMap = new HashMap<>();
		UserRoleMapping urMapping = null;
		String[] arr2 = null;
		
		for (String string : arr)
		{
			System.out.println("AA-"+string);
			
			urMapping = new UserRoleMapping();
			arr2 = string.split(":");
			urMapping.setId(Integer.parseInt(arr2[0]));
			urMapping.setUserId(Integer.parseInt(arr2[1]));
			urMapping.setRoleId(Integer.parseInt(arr2[2]));
			System.out.println(urMapping.getUserId());
			roleIdObjMap.put(urMapping.getRoleId(), urMapping);
		}
		
		userDetails.setRoleList(new ArrayList<>());
		
		userDetails.getSelectedRoles().forEach(roleId -> 
		{
			if(roleIdObjMap.containsKey(roleId))
			{
				userDetails.getRoleList().add(roleIdObjMap.get(roleId));	
			}
			else
			{
				UserRoleMapping urMap = new UserRoleMapping();
				urMap.setRoleId(roleId);
				urMap.setUserId(userDetails.getId());
				userDetails.getRoleList().add(urMap);
			}
		});
		
		String encodedPass = passwordEncoder.encode(userDetails.getPassword());
		OUT.info("Plain Pass: {}, Encoded Pass: {}", userDetails.getPassword(), encodedPass);
		userDetails.setPassword(encodedPass);

		return userDao.modifyUser(userDetails);
	}

	@Override
	public List<UserDetails> listUser()
	{
		List<UserDetails> userList = userDao.listUser();
		OUT.info("Fetched user list of size: {}", userList.size());
		
		return userList;
	}

	@Override
	public List<UserVO> listUserVO()
	{
		List<UserVO> userList = userDao.listUserVO();
		OUT.info("Fetched user list of size: {}", userList.size());
		
		return userList;
	}

	@Override
	public UserDetails getUser(int userId)
	{
		UserDetails user = userDao.getUser(userId);
		List<String> list = new ArrayList<>();
		
		user.getRoleList().forEach(mapping -> {
			user.getSelectedRoles().add(mapping.getRoleId());
			list.add(mapping.toString());
		});
		
		user.setPreviousMappings(String.join(",", list));
		
		return user;
	}

}
