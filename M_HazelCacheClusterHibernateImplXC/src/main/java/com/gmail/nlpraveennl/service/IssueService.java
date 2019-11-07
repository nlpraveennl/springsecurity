package com.gmail.nlpraveennl.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.IssueEntity;

public interface IssueService
{

	/**
	 * @return
	 */
	public Map<Integer, IssueEntity> getAll(Collection<Integer> keys);
	
	/**
	 * @return
	 */
	public List<Integer> getAllKeys();

}
