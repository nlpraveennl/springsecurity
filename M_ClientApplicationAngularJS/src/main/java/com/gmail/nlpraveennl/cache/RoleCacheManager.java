package com.gmail.nlpraveennl.cache;

import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.RoleEntity;

public interface RoleCacheManager
{
	public Map<Integer, RoleEntity> getAll();
}
