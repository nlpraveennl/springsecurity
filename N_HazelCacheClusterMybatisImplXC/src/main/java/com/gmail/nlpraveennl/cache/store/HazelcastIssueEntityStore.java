package com.gmail.nlpraveennl.cache.store;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gmail.nlpraveennl.cache.entity.IssueEntity;
import com.gmail.nlpraveennl.service.IssueService;
import com.hazelcast.core.MapLoader;
import com.hazelcast.spring.context.SpringAware;

@SpringAware
public class HazelcastIssueEntityStore implements MapLoader<Integer, IssueEntity>
{

	Logger		OUT	= LoggerFactory.getLogger(HazelcastIssueEntityStore.class);

	@Autowired
	IssueService	issueService;

	@Override
	public IssueEntity load(Integer key)
	{
		return null;
	}

	@Override
	public Map<Integer, IssueEntity> loadAll(Collection<Integer> keys)
	{
		OUT.info("Loading all issue data for keys: {}", keys);
		
		Map<Integer, IssueEntity> objMap = issueService.getAll(keys);
		OUT.info("Adding issue data to map. Input data size: {}", objMap.size());
		
		return objMap;
	}

	@Override
	public Iterable<Integer> loadAllKeys()
	{
		OUT.info("*********************Load All Issue Keys**************************");
		
		List<Integer> keyList = issueService.getAllKeys();
		OUT.info("Total issue keys found: {}", keyList.size());
		
		return keyList;
	}
}
