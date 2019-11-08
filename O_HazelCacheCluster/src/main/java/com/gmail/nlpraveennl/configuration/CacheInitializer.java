package com.gmail.nlpraveennl.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.cache.util.HazelcastCacheMapNames;
import com.gmail.nlpraveennl.cache.util.HazelcastInstanceProvider;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Component
public class CacheInitializer
{

	Logger				OUT	= LoggerFactory.getLogger(CacheInitializer.class);

	public CacheInitializer(HazelcastInstance hazelcastInstance)
	{
		System.out.println("000000000000000000");
		OUT.info("***********************************************************");
		OUT.info("Initialization of hazel cache started");
		OUT.info("***********************************************************");

		IMap<Object, Object> roleImap = hazelcastInstance.getMap(HazelcastCacheMapNames.ROLE_CACHE_IMAP);
		OUT.info("Initialized role cache. Size: {}", roleImap.size());

		IMap<Object, Object> userImap = hazelcastInstance.getMap(HazelcastCacheMapNames.USER_CACHE_IMAP);
		OUT.info("Initialized user cache. Size: {}", userImap.size());

		IMap<Object, Object> projectImap = hazelcastInstance.getMap(HazelcastCacheMapNames.PROJECT_CACHE_IMAP);
		OUT.info("Initialized project cache. Size: {}", projectImap.size());

		IMap<Object, Object> issueImap = hazelcastInstance.getMap(HazelcastCacheMapNames.ISSUE_CACHE_IMAP);
		OUT.info("Initialized issue cache. Size: {}", issueImap.size());

		OUT.info("***********************************************************");
		OUT.info("Initialization of hazel cache completed");
		OUT.info("***********************************************************");
		
		HazelcastInstanceProvider.getInstance().setHazelcastInstance(hazelcastInstance);
	}
}
