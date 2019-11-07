package com.gmail.nlpraveennl.dao.impl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.cache.entity.RoleEntity;
import com.gmail.nlpraveennl.dao.RoleDao;

@Component
public class RoleDaoImpl implements RoleDao
{

	Logger			OUT	= LoggerFactory.getLogger(RoleDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public List<RoleEntity> getAll(Collection<Integer> keys)
	{
		String commaSeparatedKeys = keys.stream().map(String::valueOf).collect(Collectors.joining(","));
		try (Session session = sessionFactory.openSession();)
		{
			return session.createQuery("SELECT new com.gmail.nlpraveennl.cache.entity.RoleEntity(rm.id, rm.name) FROM RoleMaster rm WHERE id IN("+commaSeparatedKeys+")", RoleEntity.class).list();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public List<Integer> getAllKeys()
	{
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<Integer> query = session.createQuery("SELECT id FROM RoleMaster", Integer.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			OUT.error("Exception - {}", e);
			return null;
		}
	}
}
