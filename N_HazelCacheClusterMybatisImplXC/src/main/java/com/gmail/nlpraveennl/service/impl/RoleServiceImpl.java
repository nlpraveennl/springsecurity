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

import com.gmail.nlpraveennl.cache.entity.RoleEntity;
import com.gmail.nlpraveennl.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{

	Logger	OUT	= LoggerFactory.getLogger(RoleServiceImpl.class);
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public Map<Integer, RoleEntity> getAll(Collection<Integer> keys)
	{
		Map<String, Object> map = new HashMap<>();
		map.put("keys", keys);
		List<RoleEntity> roleList = sqlSession.selectList("RoleMapper.getRoles", map);
		
		return roleList.stream().collect(Collectors.toMap(RoleEntity::getId,
                Function.identity()));
	}

	@Override
	public List<Integer> getAllKeys()
	{
		return sqlSession.selectList("RoleMapper.getAllKeys");
	}
}
