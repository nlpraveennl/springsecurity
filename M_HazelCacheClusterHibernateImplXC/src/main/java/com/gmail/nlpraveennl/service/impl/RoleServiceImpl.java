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

import com.gmail.nlpraveennl.cache.entity.RoleEntity;
import com.gmail.nlpraveennl.dao.RoleDao;
import com.gmail.nlpraveennl.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{

	Logger	OUT	= LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleDao	roleDao;

	@Override
	public Map<Integer, RoleEntity> getAll(Collection<Integer> keys)
	{
		List<RoleEntity> roleList = roleDao.getAll(keys);
		
//		Map<Integer, RoleEntity> roleMap = new HashMap<>();
//		roleList.forEach(role -> {
//			roleMap.put(role.getId(), role);
//		});
		return roleList.stream().collect(Collectors.toMap(RoleEntity::getId,
                Function.identity()));
	}

	@Override
	public List<Integer> getAllKeys()
	{
		return roleDao.getAllKeys();
	}
}
