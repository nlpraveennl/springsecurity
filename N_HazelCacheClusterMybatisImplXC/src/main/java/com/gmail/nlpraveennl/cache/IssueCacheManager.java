package com.gmail.nlpraveennl.cache;

import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.IssueEntity;

public interface IssueCacheManager
{
	public Map<Integer, IssueEntity> getAll();
}
