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

import com.gmail.nlpraveennl.cache.entity.IssueEntity;
import com.gmail.nlpraveennl.dao.IssueDao;

@Component
public class IssueDaoImpl implements IssueDao
{
	Logger			OUT	= LoggerFactory.getLogger(IssueDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public List<IssueEntity> getAll(Collection<Integer> keys)
	{
		String commaSeparatedKeys = keys.stream().map(String::valueOf).collect(Collectors.joining(","));
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<IssueEntity> query = session.createQuery(""
					+ "SELECT NEW com.gmail.nlpraveennl.cache.entity.IssueEntity(i.id, i.name, i.summary, i.description, i.issueType, i.reporter, i.assignee, i.projectId, udrep.userName, udasn.userName, proj.name)"
					+ "FROM Issue i "
					+ "INNER JOIN UserDetails udrep ON udrep.id = i.reporter "
					+ "INNER JOIN UserDetails udasn ON udasn.id = i.assignee "
					+ "INNER JOIN Project proj ON proj.id = i.projectId "
					+ "WHERE i.id IN("+commaSeparatedKeys+")", IssueEntity.class);
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
			TypedQuery<Integer> query = session.createQuery("SELECT id FROM Issue", Integer.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}
}
