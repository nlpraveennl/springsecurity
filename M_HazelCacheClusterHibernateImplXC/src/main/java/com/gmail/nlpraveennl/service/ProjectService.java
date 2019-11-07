package com.gmail.nlpraveennl.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.ProjectEntity;

public interface ProjectService
{
	
	/**
	 * @return
	 */
	public Map<Integer, ProjectEntity> getAll(Collection<Integer> keys);
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();
	
}
