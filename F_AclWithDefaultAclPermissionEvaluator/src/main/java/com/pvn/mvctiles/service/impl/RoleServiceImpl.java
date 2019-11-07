package com.pvn.mvctiles.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvn.mvctiles.dao.RoleDao;
import com.pvn.mvctiles.model.RoleMaster;
import com.pvn.mvctiles.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{

	Logger	OUT	= LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleDao	roleDao;

	@Override
	public List<RoleMaster> getAll()
	{
		return roleDao.getAll();
	}
}
