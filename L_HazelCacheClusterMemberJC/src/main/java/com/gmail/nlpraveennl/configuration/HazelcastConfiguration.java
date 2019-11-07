package com.gmail.nlpraveennl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gmail.nlpraveennl.cache.store.HazelcastUserEntityStore;
import com.gmail.nlpraveennl.cache.util.HazelcastConstants;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.config.MapStoreConfig.InitialLoadMode;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class HazelcastConfiguration
{
	@Autowired
	Config hazelCastConfig;
	
	@Bean
	public Config hazelCastConfig()
	{
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<Config Bean>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		Config hzCacheConfig = new Config();
		
		hzCacheConfig.getGroupConfig().setName("praveen").setPassword("praveen@123#");
		
		hzCacheConfig.getNetworkConfig().setPort(5900)
        			 .setPortAutoIncrement(true);
		
		NearCacheConfig nearCacheConfig = new NearCacheConfig();
		nearCacheConfig.setTimeToLiveSeconds(0);
		nearCacheConfig.setMaxIdleSeconds(0);
		nearCacheConfig.setInvalidateOnChange(true);
		nearCacheConfig.setEvictionPolicy("NONE");
		
		MapConfig cacheMapConfig = new MapConfig();
		cacheMapConfig.setName(HazelcastConstants.USER_CACHE_IMAP);
		cacheMapConfig.setNearCacheConfig(nearCacheConfig);
		
		MapStoreConfig mapStoreConfig = new MapStoreConfig();
		mapStoreConfig.setClassName(HazelcastUserEntityStore.class.getName()).setEnabled(true);
		mapStoreConfig.setInitialLoadMode(InitialLoadMode.EAGER);
		cacheMapConfig.setMapStoreConfig(mapStoreConfig);
		hzCacheConfig.addMapConfig(cacheMapConfig);
		
		return hzCacheConfig;
	}
	
	@Bean
	public HazelcastInstance hazelcastInstance()
	{
		System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{Instance Bean}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
		HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance(hazelCastConfig);
		
		hzInstance.getMap(HazelcastConstants.USER_CACHE_IMAP);
		return hzInstance;
	}
}
