package com.pvn.mvctiles.dao.impl;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.pvn.mvctiles.dao.UserDao;
import com.pvn.mvctiles.model.DbUserDetails;
import com.pvn.mvctiles.model.RoleMaster;

@Component
public class UserDaoImpl implements UserDao
{

	Logger							OUT		= LoggerFactory.getLogger(UserDaoImpl.class);

	private BCryptPasswordEncoder	bcrypt	= new BCryptPasswordEncoder();

	@Autowired
	SessionFactory					sessionFactory;

	@SuppressWarnings("unchecked")
	public List<GrantedAuthority> authenticate(String username, String password)
	{
		try (Session session = sessionFactory.openSession();)
		{
			Query<DbUserDetails> userQuery = session.createQuery("FROM DbUserDetails WHERE userName = ?1");
			userQuery.setParameter(1, username);

			DbUserDetails dbUser = userQuery.getSingleResult();

			if (dbUser != null && bcrypt.matches(password, dbUser.getPassword()))
			{
				CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
				CriteriaQuery<RoleMaster> roleCriteria = criteriaBuilder.createQuery(RoleMaster.class);
				Root<RoleMaster> roleRoot = roleCriteria.from(RoleMaster.class);
				roleCriteria.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("id"), dbUser.getRoleId()));

				Query<RoleMaster> roleQuery = session.createQuery(roleCriteria);
				RoleMaster role = roleQuery.getSingleResult();

				List<GrantedAuthority> authList = new ArrayList<>();
				authList.add(new SimpleGrantedAuthority(role.getName()));

				return authList;
			}
			else
			{
				return new ArrayList<>();
			}
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return new ArrayList<>();
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
			if (tx != null && tx.isActive())
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
			if (tx != null && tx.isActive())
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
			Query<DbUserDetails> query = session.createQuery("FROM DbUserDetails WHERE id = " + userId);
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
