package com.gmail.nlpraveennl.cache;

import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.ProjectEntity;

public interface ProjectCacheManager
{
	public Map<Integer, ProjectEntity> getAll();
}
