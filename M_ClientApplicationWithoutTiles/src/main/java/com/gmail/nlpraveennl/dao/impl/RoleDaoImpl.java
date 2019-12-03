package com.gmail.nlpraveennl.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.dao.RoleDao;
import com.gmail.nlpraveennl.model.RoleMaster;

@Component
public class RoleDaoImpl implements RoleDao
{

	Logger			OUT	= LoggerFactory.getLogger(RoleDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public List<RoleMaster> getAll()
	{
		try (Session session = sessionFactory.openSession();)
		{
			return session.createQuery("FROM RoleMaster", RoleMaster.class).list();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

}
