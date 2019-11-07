package com.gmail.nlpraveennl.dao;

import java.util.Collection;
import java.util.List;

import com.gmail.nlpraveennl.cache.entity.RoleEntity;

public interface RoleDao
{

	/**
	 * @param keys 
	 * @return
	 */
	public List<RoleEntity> getAll(Collection<Integer> keys);
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();
}
