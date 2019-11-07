package com.gmail.nlpraveennl.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.cache.entity.IssueEntity;
import com.gmail.nlpraveennl.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService
{

	Logger	OUT	= LoggerFactory.getLogger(IssueServiceImpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Map<Integer, IssueEntity> getAll(Collection<Integer> keys)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("keys", keys);
		
		List<IssueEntity> issueList = sqlSession.selectList("IssueMapper.getIssues", map);
		
		return issueList.stream().collect(Collectors.toMap(IssueEntity::getId,
                Function.identity()));
	}

	@Override
	public List<Integer> getAllKeys()
	{
		return sqlSession.selectList("IssueMapper.getAllKeys");
	}

}
