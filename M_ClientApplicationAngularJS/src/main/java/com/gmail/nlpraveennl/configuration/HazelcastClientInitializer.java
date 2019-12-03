package com.gmail.nlpraveennl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.gmail.nlpraveennl.cache.util.HazelcastInstanceProvider;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;

@Configuration
@Order(1)
public class HazelcastClientInitializer
{
	@Bean
	public HazelcastInstance hazelcastInstance()
	{
		ClientConfig config = new ClientConfig();
    	
		GroupConfig groupConfig = config.getGroupConfig();
		groupConfig.setName("dev");
		
		HazelcastInstance hzInstance = HazelcastClient.newHazelcastClient(config);
		HazelcastInstanceProvider.getInstance().setHazelcastInstance(hzInstance);
		
		return hzInstance;
	}
}
