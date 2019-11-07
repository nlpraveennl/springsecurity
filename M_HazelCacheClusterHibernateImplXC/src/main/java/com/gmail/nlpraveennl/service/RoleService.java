package com.gmail.nlpraveennl.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.RoleEntity;

public interface RoleService
{

	/**
	 * @return
	 */
	public Map<Integer, RoleEntity> getAll(Collection<Integer> keys);
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();

}
