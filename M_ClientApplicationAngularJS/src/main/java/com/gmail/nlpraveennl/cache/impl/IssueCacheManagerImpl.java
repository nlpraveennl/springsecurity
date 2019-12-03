package com.gmail.nlpraveennl.cache.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.cache.IssueCacheManager;
import com.gmail.nlpraveennl.cache.entity.IssueEntity;
import com.gmail.nlpraveennl.cache.util.HazelcastCacheMapNames;
import com.gmail.nlpraveennl.util.ApplicationConstants;
import com.hazelcast.core.HazelcastInstance;

@Service
public class IssueCacheManagerImpl implements IssueCacheManager
{
	Logger	OUT	= LoggerFactory.getLogger(IssueCacheManagerImpl.class);
	
	@Autowired
	HazelcastInstance hazelCastInstance;

	@Override
	public Map<Integer, IssueEntity> getAll()
	{
		try
		{
			return hazelCastInstance.getMap(HazelcastCacheMapNames.ISSUE_CACHE_IMAP);
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			return null;
		}
	}

}
