package com.gmail.nlpraveennl.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.cache.entity.UserEntity;
import com.gmail.nlpraveennl.dao.UserDao;
import com.gmail.nlpraveennl.service.UserService;
import com.gmail.nlpraveennl.vo.UserVO;

@Service
public class UserServiceImpl implements UserService
{

	Logger	OUT	= LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserDao	userDao;
	
	@Override
	public Map<Integer, UserEntity> getAll(Collection<Integer> keys)
	{
		List<UserVO> userVos = userDao.getAll(keys);
		Map<Integer, UserEntity> entityMap = new HashMap<>();
		
		userVos.forEach(vo -> {
			if(!entityMap.containsKey(vo.getId()))
			{
				UserEntity entity = new UserEntity(vo);
				
				entity.setRoleIds(String.valueOf(vo.getRoleId()));
				entity.setRoleStrs(String.valueOf(vo.getRoleStr()));
				
				entityMap.put(entity.getId(), entity);
			}
			else
			{
				UserEntity entity = entityMap.get(vo.getId());
				
				entity.setRoleIds(entity.getRoleIds() + "," + String.valueOf(vo.getRoleId()));
				entity.setRoleStrs(entity.getRoleStrs() + "," + String.valueOf(vo.getRoleStr()));
			}
		});
		
		return entityMap;
	}

	@Override
	public List<Integer> getAllKeys()
	{
		return userDao.getAllKeys();
	}
}
