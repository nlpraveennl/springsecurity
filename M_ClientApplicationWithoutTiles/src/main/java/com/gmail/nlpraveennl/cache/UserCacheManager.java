package com.gmail.nlpraveennl.cache;

import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.UserEntity;

public interface UserCacheManager
{
	public Map<Integer, UserEntity> getAll();
	
	public Boolean addUserEntity(UserEntity userEntity);
	
	public Boolean updateUserEntity(UserEntity userEntity);
	
}
