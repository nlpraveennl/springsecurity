package com.gmail.nlpraveennl.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.UserEntity;

public interface UserService
{
	
	
	/**
	 * @param keys
	 * @return
	 */
	public Map<Integer, UserEntity> getAll(Collection<Integer> keys);
	
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();

}
