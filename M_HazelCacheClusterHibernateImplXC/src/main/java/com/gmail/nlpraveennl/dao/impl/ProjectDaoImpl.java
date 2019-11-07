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

import com.gmail.nlpraveennl.cache.entity.ProjectEntity;
import com.gmail.nlpraveennl.dao.ProjectDao;

@Component
public class ProjectDaoImpl implements ProjectDao
{

	Logger			OUT	= LoggerFactory.getLogger(ProjectDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public List<ProjectEntity> getAll(Collection<Integer> keys)
	{
		String commaSeparatedKeys = keys.stream().map(String::valueOf).collect(Collectors.joining(","));
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<ProjectEntity> query = session.createQuery("SELECT NEW com.gmail.nlpraveennl.cache.entity.ProjectEntity(p.id, p.name, p.description, p.mgr, ud.userName) "
					+ "FROM Project p INNER JOIN UserDetails ud ON ud.id = p.mgr "
					+ "WHERE p.id IN("+commaSeparatedKeys+")", ProjectEntity.class);
			return query.getResultList();
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
			TypedQuery<Integer> query = session.createQuery("SELECT id FROM Project", Integer.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}
}
