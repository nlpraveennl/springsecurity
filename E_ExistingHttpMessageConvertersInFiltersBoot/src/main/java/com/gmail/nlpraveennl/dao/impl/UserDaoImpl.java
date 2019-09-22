package com.gmail.nlpraveennl.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.dao.UserDao;
import com.gmail.nlpraveennl.model.UserDetails;

@Component
public class UserDaoImpl implements UserDao
{

	Logger			OUT	= LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

//	Method not used in this project
	@Override
	public UserDetails authenticateUser(UserDetails userDetails)
	{
		try (Session session = sessionFactory.openSession();)
		{
			@SuppressWarnings("unchecked")
			Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE userName = ?1 AND password = ?2");
			query.setParameter(1, userDetails.getUserName());
			query.setParameter(2, userDetails.getPassword());
			OUT.info("{}", query.getQueryString());
			List<UserDetails> list = query.getResultList();
			OUT.info("{}" + list.size());
			if (list.size() == 1)
				return list.get(0);
			else
				return null;
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public UserDetails addUser(UserDetails userDetails)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.save(userDetails);
			tx.commit();
			return userDetails;
		}
		catch (Exception e)
		{
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public UserDetails modifyUser(UserDetails userDetails)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.update(userDetails);
			tx.commit();
			return userDetails;
		}
		catch (Exception e)
		{
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public List<UserDetails> listUser()
	{
		try (Session session = sessionFactory.openSession();)
		{
			@SuppressWarnings("unchecked")
			Query<UserDetails> query = session.createQuery("FROM UserDetails");
			OUT.info("{}", query.getQueryString());
			List<UserDetails> list = query.getResultList();
			return list;
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public UserDetails getUser(int userId)
	{
		try (Session session = sessionFactory.openSession();)
		{
			@SuppressWarnings("unchecked")
			Query<UserDetails> query = session.createQuery("FROM UserDetails WHERE id = "+userId);
			OUT.info("{}", query.getQueryString());
			UserDetails user = query.uniqueResult();
			return user;
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			throw e;
		}
	}

}
