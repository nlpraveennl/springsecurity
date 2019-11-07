package com.gmail.nlpraveennl;

import java.util.Collection;

import com.gmail.nlpraveennl.cache.entity.UserCacheEntity;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("deprecation")
	public static void main( String[] args )
    {
    	ClientConfig config = new ClientConfig();
    	
		GroupConfig groupConfig = config.getGroupConfig();
		groupConfig.setName("dev");
		groupConfig.setPassword("password");
		
		HazelcastInstance hzClient
		  = HazelcastClient.newHazelcastClient(config);
		
		IMap<Object, UserCacheEntity> imap = hzClient.getMap("userMap");
		System.out.println(imap.size());
		
		Collection<UserCacheEntity> coll = imap.values();
		coll.forEach(entity -> {
			System.out.println(entity);
		});
		
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		hzClient.shutdown();
    }
}
