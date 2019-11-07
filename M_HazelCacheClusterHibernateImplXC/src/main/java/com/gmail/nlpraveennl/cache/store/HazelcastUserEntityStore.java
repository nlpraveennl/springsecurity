package com.gmail.nlpraveennl.cache.store;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.nlpraveennl.cache.entity.UserEntity;
import com.gmail.nlpraveennl.service.UserService;
import com.hazelcast.core.MapLoader;
import com.hazelcast.spring.context.SpringAware;

@SpringAware
public class HazelcastUserEntityStore implements MapLoader<Integer, UserEntity>
{

	Logger		OUT	= LoggerFactory.getLogger(HazelcastUserEntityStore.class);

	@Autowired
	UserService	userService;

	@Override
	public UserEntity load(Integer key)
	{
		return null;
	}

	@Override
	public Map<Integer, UserEntity> loadAll(Collection<Integer> keys)
	{
		OUT.info("Loading all user data for keys: {}", keys);
		
		Map<Integer, UserEntity> objMap = userService.getAll(keys);
		OUT.info("Adding user data to map. Input data size: {}", objMap.size());
		
		return objMap;
	}

	@Override
	public Iterable<Integer> loadAllKeys()
	{
		OUT.info("*********************Load All User Keys**************************");
		
		List<Integer> keyList = userService.getAllKeys();
		OUT.info("Total user keys found: {}", keyList.size());
		
		return keyList;
	}
}
