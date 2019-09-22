package com.gmail.nlpraveennl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.dao.UserDao;
import com.gmail.nlpraveennl.model.DbUserDetails;
import com.gmail.nlpraveennl.model.RoleMaster;

@Component
public class UserDaoImpl implements UserDao, UserDetailsService
{

	Logger			OUT	= LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		try (Session session = sessionFactory.openSession();)
		{
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<DbUserDetails> userCriteria = criteriaBuilder.createQuery(DbUserDetails.class);
			Root<DbUserDetails> userRoot = userCriteria.from(DbUserDetails.class);
			userCriteria.select(userRoot).where(criteriaBuilder.equal(userRoot.get("userName"), username));
			
			Query<DbUserDetails> userQuery =session.createQuery(userCriteria);
			DbUserDetails dbUser = userQuery.getSingleResult();
			
			CriteriaQuery<RoleMaster> roleCriteria = criteriaBuilder.createQuery(RoleMaster.class);
			Root<RoleMaster> roleRoot = roleCriteria.from(RoleMaster.class);
			roleCriteria.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("id"), dbUser.getRoleId()));
			
			Query<RoleMaster> roleQuery =session.createQuery(roleCriteria);
			RoleMaster role = roleQuery.getSingleResult();
			
			List<GrantedAuthority> authList = new ArrayList<>();
			authList.add(new SimpleGrantedAuthority(role.getName()));
			
			return new User(username, dbUser.getPassword(),true, true, true, true, authList);
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			throw new UsernameNotFoundException("Exception caught", e);
		}
	}

//	Method not used in this project
	@Override
	public DbUserDetails authenticateUser(DbUserDetails userDetails)
	{
		try (Session session = sessionFactory.openSession();)
		{
			@SuppressWarnings("unchecked")
			Query<DbUserDetails> query = session.createQuery("FROM DbUserDetails WHERE userName = ?1 AND password = ?2");
			query.setParameter(1, userDetails.getUserName());
			query.setParameter(2, userDetails.getPassword());
			OUT.info("{}", query.getQueryString());
			List<DbUserDetails> list = query.getResultList();
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
	public DbUserDetails addUser(DbUserDetails userDetails)
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
	public DbUserDetails modifyUser(DbUserDetails userDetails)
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
	public List<DbUserDetails> listUser()
	{
		try (Session session = sessionFactory.openSession();)
		{
			@SuppressWarnings("unchecked")
			Query<DbUserDetails> query = session.createQuery("FROM DbUserDetails");
			OUT.info("{}", query.getQueryString());
			List<DbUserDetails> list = query.getResultList();
			return list;
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public DbUserDetails getUser(int userId)
	{
		try (Session session = sessionFactory.openSession();)
		{
			@SuppressWarnings("unchecked")
			Query<DbUserDetails> query = session.createQuery("FROM DbUserDetails WHERE id = "+userId);
			OUT.info("{}", query.getQueryString());
			DbUserDetails user = query.uniqueResult();
			return user;
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			throw e;
		}
	}
}
