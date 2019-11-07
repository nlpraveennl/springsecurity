package com.gmail.nlpraveennl.cache.store;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.nlpraveennl.cache.entity.ProjectEntity;
import com.gmail.nlpraveennl.service.ProjectService;
import com.hazelcast.core.MapLoader;
import com.hazelcast.spring.context.SpringAware;

@SpringAware
public class HazelcastProjectEntityStore implements MapLoader<Integer, ProjectEntity>
{

	Logger		OUT	= LoggerFactory.getLogger(HazelcastProjectEntityStore.class);

	@Autowired
	ProjectService	projectService;

	@Override
	public ProjectEntity load(Integer key)
	{
		return null;
	}

	@Override
	public Map<Integer, ProjectEntity> loadAll(Collection<Integer> keys)
	{
		OUT.info("Loading all project data for keys: {}", keys);

		Map<Integer, ProjectEntity> objMap = projectService.getAll(keys);
		OUT.info("Adding project data to map. Input data size: {}", objMap.size());
		
		return objMap;
	}

	@Override
	public Iterable<Integer> loadAllKeys()
	{
		OUT.info("*********************Load All Project Keys**************************");
		
		List<Integer> keyList = projectService.getAllKeys();
		OUT.info("Total project keys found: {}", keyList.size());
		
		return keyList;
	}
}
