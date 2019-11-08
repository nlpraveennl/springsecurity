package com.gmail.nlpraveennl.cacheservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.cache.RoleCacheManager;
import com.gmail.nlpraveennl.cache.UserCacheManager;
import com.gmail.nlpraveennl.cache.entity.RoleEntity;
import com.gmail.nlpraveennl.cache.entity.UserEntity;
import com.gmail.nlpraveennl.cacheservice.UserCacheUpdateSevice;
import com.gmail.nlpraveennl.model.UserDetails;

@Component
public class UserCacheUpdateServiceImpl implements UserCacheUpdateSevice
{
	Logger	OUT	= LoggerFactory.getLogger(UserCacheUpdateServiceImpl.class);
	
	@Autowired
	UserCacheManager userCache;
	
	@Autowired
	RoleCacheManager roleCache;

	@Override
	public boolean addUserEntity(UserDetails userDetails)
	{
		UserEntity entity = createUserEntity(userDetails);
		
		OUT.info("Adding user entity to cache. id: {}", entity.getId());
		boolean added = userCache.addUserEntity(entity);
		if(added)
		{
			OUT.info("Added user entity to cache. id: {}", entity.getId());
			return true;
		}
		else
		{
			OUT.info("Failed to add user entity to cache. id: {}", entity.getId());
			return false;
		}
	}
	
	@Override
	public boolean updateUserEntity(UserDetails userDetails)
	{
		UserEntity entity = createUserEntity(userDetails);
		
		OUT.info("Adding user entity to cache. id: {}", entity.getId());
		boolean updated = userCache.updateUserEntity(entity);
		if(updated)
		{
			OUT.info("Updated user entity to cache. id: {}", entity.getId());
			return true;
		}
		else
		{
			OUT.info("Failed to update user entity to cache. id: {}", entity.getId());
			return false;
		}
	}

	private UserEntity createUserEntity(UserDetails userDetails)
	{
		Map<Integer, RoleEntity> roleMap = roleCache.getAll();
		
		UserEntity entity = new UserEntity();
		entity.setId(userDetails.getId());
		entity.setFirstName(userDetails.getFirstName());
		entity.setLastName(userDetails.getLastName());
		entity.setUserName(userDetails.getUserName());
		entity.setEmail(userDetails.getEmail());
		entity.setGender(userDetails.getGender());
		entity.setEnabled(userDetails.isEnabled());
		
		List<String> idList = new ArrayList<String>();
		List<String> nameList = new ArrayList<String>();
		
		userDetails.getRoleList().forEach(role -> {
			idList.add(String.valueOf(role.getRoleId()));
			RoleEntity roleEntity = roleMap.get(role.getRoleId());
			if(roleEntity != null)
			{
				nameList.add(roleEntity.getName());
			}
		});
		
		entity.setRoleIds(String.join(",", idList));
		entity.setRoleStrs(String.join(",", nameList));
		return entity;
	}
}
