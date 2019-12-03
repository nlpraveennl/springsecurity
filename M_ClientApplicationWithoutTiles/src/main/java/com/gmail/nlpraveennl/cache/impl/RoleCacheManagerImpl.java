package com.gmail.nlpraveennl.cache.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.cache.RoleCacheManager;
import com.gmail.nlpraveennl.cache.entity.RoleEntity;
import com.gmail.nlpraveennl.cache.util.HazelcastCacheMapNames;
import com.gmail.nlpraveennl.util.ApplicationConstants;
import com.hazelcast.core.HazelcastInstance;

@Service
public class RoleCacheManagerImpl implements RoleCacheManager
{
	Logger	OUT	= LoggerFactory.getLogger(RoleCacheManagerImpl.class);
	
	@Autowired
	HazelcastInstance hazelCastInstance;

	@Override
	public Map<Integer, RoleEntity> getAll()
	{
		try
		{
			return hazelCastInstance.getMap(HazelcastCacheMapNames.ROLE_CACHE_IMAP);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return null;
		}
	}

}
