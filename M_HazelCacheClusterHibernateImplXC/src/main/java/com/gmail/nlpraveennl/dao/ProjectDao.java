package com.gmail.nlpraveennl.dao;

import java.util.Collection;
import java.util.List;

import com.gmail.nlpraveennl.cache.entity.ProjectEntity;

public interface ProjectDao
{
	/**
	 * @return
	 */
	public List<ProjectEntity> getAll(Collection<Integer> keys);
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();
}
