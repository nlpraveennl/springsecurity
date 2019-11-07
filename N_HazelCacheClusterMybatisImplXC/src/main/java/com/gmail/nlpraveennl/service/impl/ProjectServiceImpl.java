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

import com.gmail.nlpraveennl.cache.entity.ProjectEntity;
import com.gmail.nlpraveennl.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService
{

	Logger	OUT	= LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Map<Integer, ProjectEntity> getAll(Collection<Integer> keys)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("keys", keys);
		
		List<ProjectEntity> projectList = sqlSession.selectList("ProjectMapper.getProjects", map);
		
		return projectList.stream().collect(Collectors.toMap(ProjectEntity::getId,
                Function.identity()));
	}

	@Override
	public List<Integer> getAllKeys()
	{
		return sqlSession.selectList("ProjectMapper.getAllKeys");
	}
}
