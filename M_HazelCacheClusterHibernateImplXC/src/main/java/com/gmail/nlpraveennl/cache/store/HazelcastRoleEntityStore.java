package com.gmail.nlpraveennl.cache.store;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.nlpraveennl.cache.entity.RoleEntity;
import com.gmail.nlpraveennl.service.RoleService;
import com.hazelcast.core.MapLoader;
import com.hazelcast.spring.context.SpringAware;

@SpringAware
public class HazelcastRoleEntityStore implements MapLoader<Integer, RoleEntity>
{

	Logger		OUT	= LoggerFactory.getLogger(HazelcastRoleEntityStore.class);

	@Autowired
	RoleService	roleService;

	@Override
	public RoleEntity load(Integer key)
	{
		return null;
	}

	@Override
	public Map<Integer, RoleEntity> loadAll(Collection<Integer> keys)
	{
		OUT.info("Loading all role data for keys: {}", keys);
		
		Map<Integer, RoleEntity> objMap = roleService.getAll(keys);
		OUT.info("Adding role data to map. Input data size: {}", objMap.size());
		
		return objMap;
	}

	@Override
	public Iterable<Integer> loadAllKeys()
	{
		OUT.info("*********************Load All Role Keys**************************");
		
		List<Integer> keyList = roleService.getAllKeys();
		OUT.info("Total role keys found: {}", keyList.size());
		
		return keyList;
	}
}
