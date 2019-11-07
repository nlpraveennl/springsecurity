package com.gmail.nlpraveennl.cache.store;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.gmail.nlpraveennl.cache.entity.UserCacheEntity;
import com.hazelcast.core.MapLoader;

public class HazelcastUserEntityStore implements MapLoader<Integer, UserCacheEntity>
{

	@Override
	public UserCacheEntity load(Integer key)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, UserCacheEntity> loadAll(Collection<Integer> keys)
	{
		System.out.println("*********************<<<<<<LOAD-ALL>>>>>>**************************");
		Map<Integer, UserCacheEntity> map = new HashMap<>();
		
		keys.forEach(key ->{
			UserCacheEntity entity = new UserCacheEntity(key, "fn"+key, "ln", "usename"+key, "password", "email"+key, "MALE", true, 1, "ROLE_ADMIN");
			map.put(entity.getId(), entity);
		});
		
		return map;
	}

	@Override
	public Iterable<Integer> loadAllKeys()
	{
		System.out.println("*********************<<<<<<LOAD-ALL-KEYS>>>>>>**************************");
		return Arrays.asList(1,2,3);
	}

}
