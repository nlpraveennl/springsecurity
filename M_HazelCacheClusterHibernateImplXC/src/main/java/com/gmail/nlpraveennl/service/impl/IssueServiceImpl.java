package com.gmail.nlpraveennl.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.cache.entity.IssueEntity;
import com.gmail.nlpraveennl.dao.IssueDao;
import com.gmail.nlpraveennl.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService
{

	Logger	OUT	= LoggerFactory.getLogger(IssueServiceImpl.class);
	
	@Autowired
	private IssueDao issueDao;

	@Override
	public Map<Integer, IssueEntity> getAll(Collection<Integer> keys)
	{
		List<IssueEntity> issueList = issueDao.getAll(keys);
		
		return issueList.stream().collect(Collectors.toMap(IssueEntity::getId,
                Function.identity()));
	}

	@Override
	public List<Integer> getAllKeys()
	{
		return issueDao.getAllKeys();
	}

}
