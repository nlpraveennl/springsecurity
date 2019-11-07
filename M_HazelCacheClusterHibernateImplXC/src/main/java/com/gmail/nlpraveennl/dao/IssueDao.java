package com.gmail.nlpraveennl.dao;

import java.util.Collection;
import java.util.List;

import com.gmail.nlpraveennl.cache.entity.IssueEntity;

public interface IssueDao
{

	/**
	 * @param keys 
	 * @return
	 */
	public List<IssueEntity> getAll(Collection<Integer> keys);
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();

}
