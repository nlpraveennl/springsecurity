package com.gmail.nlpraveennl.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.cache.UserCacheManager;
import com.gmail.nlpraveennl.cache.entity.UserEntity;
import com.gmail.nlpraveennl.cacheservice.UserCacheUpdateSevice;
import com.gmail.nlpraveennl.dao.UserDao;
import com.gmail.nlpraveennl.model.UserDetails;
import com.gmail.nlpraveennl.model.UserRoleMapping;
import com.gmail.nlpraveennl.service.UserService;
import com.gmail.nlpraveennl.vo.UserVO;

@Service
public class UserServiceImpl implements UserService
{

	Logger	OUT	= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao	userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserCacheManager userCache;
	
	@Autowired
	UserCacheUpdateSevice userCacheUpdateSevice;
	
	@Override
	public UserDetails addUser(UserDetails userDetails)
	{
		userDetails.setRoleList(new ArrayList<>());
		
		userDetails.getSelectedRoles().forEach(roleId -> {
			UserRoleMapping mapping = new UserRoleMapping();
			mapping.setRoleId(roleId);
			
			userDetails.getRoleList().add(mapping);
		});
		
		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		userDetails.setEnabled(true);
		
		UserDetails returnObj = userDao.addUser(userDetails);
		
		if(returnObj != null)
		{
			userCacheUpdateSevice.addUserEntity(userDetails);
		}

		return returnObj;
	}

	@Override
	public UserDetails modifyUser(UserDetails userDetails)
	{
		userDetails.setEnabled(true);
		String[] arr = userDetails.getPreviousMappings().split(",");
		Map<Integer, UserRoleMapping> roleIdObjMap = new HashMap<>();
		UserRoleMapping urMapping = null;
		String[] arr2 = null;
		
		for (String string : arr)
		{
			urMapping = new UserRoleMapping();
			arr2 = string.split(":");
			urMapping.setId(Integer.parseInt(arr2[0]));
			urMapping.setUserId(Integer.parseInt(arr2[1]));
			urMapping.setRoleId(Integer.parseInt(arr2[2]));
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
		
		userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));

		UserDetails returnObj = userDao.modifyUser(userDetails);
		
		if(returnObj != null)
		{
			userCacheUpdateSevice.updateUserEntity(userDetails);
		}
		
		return returnObj;
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
		Map<Integer, UserEntity> userMap = userCache.getAll();
		OUT.info("User Map from cache. Size: {}", userMap.size());
		
		List<UserEntity> entityList = new ArrayList<>(userMap.values());
		List<UserVO> voList = new ArrayList<>();
		
		entityList.forEach(entity -> {
			voList.add(new UserVO(entity));
		});
		
		OUT.info("Fetched user list of size: {}", voList.size());
		
		Collections.sort(voList, new Comparator<UserVO>()
		{
			@Override
			public int compare(UserVO o1, UserVO o2)
			{
				return o1.getId() - o2.getId();
			}
		});
		
		return voList;
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
