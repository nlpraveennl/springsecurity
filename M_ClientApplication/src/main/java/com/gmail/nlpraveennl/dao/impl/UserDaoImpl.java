package com.gmail.nlpraveennl.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;

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
import com.gmail.nlpraveennl.model.UserRoleMapping;
import com.gmail.nlpraveennl.vo.UserVO;

@Component
public class UserDaoImpl implements UserDao
{

	Logger			OUT	= LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public UserDetails addUser(UserDetails userDetails)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.save(userDetails);
			System.out.println("userDetails.getId"+userDetails.getId());
			userDetails.getRoleList().forEach(role -> {
				System.out.println("role.getRoleId"+role.getRoleId());
				role.setUserId(userDetails.getId());
				session.save(role);
			});
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

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails modifyUser(UserDetails userDetails)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.update(userDetails);
			
			String commaSeparatedSelectedRoles = userDetails.getSelectedRoles().stream().map(String::valueOf).collect(Collectors.joining(","));
			
			Query<UserRoleMapping> query = session.createQuery("DELETE FROM UserRoleMapping WHERE userId = "+userDetails.getId()+" AND roleId NOT IN("+commaSeparatedSelectedRoles+")");
			System.out.println("commaSeparatedSelectedRoles:"+commaSeparatedSelectedRoles);
			System.out.println(query.getQueryString());
			
			int rolesDeletedCount = query.executeUpdate();
			System.out.println("rolesDeletedCount:"+rolesDeletedCount);
			if(rolesDeletedCount>0)
			{
				System.out.println(rolesDeletedCount+" roles deleted!");
			}
			
			userDetails.getRoleList().forEach(roleMapping -> 
			{
				session.saveOrUpdate(roleMapping);
			});
			tx.commit();
			return userDetails;
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
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
	public List<UserVO> listUserVO()
	{
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<UserVO> query = session.createQuery("SELECT NEW com.gmail.nlpraveennl.vo.UserVO(ud.id, ud.firstName, ud.lastName, ud.userName, ud.password, ud.email, ud.gender, ud.enabled, rm.id, rm.name) "
					+ "FROM UserDetails ud INNER JOIN UserRoleMapping map ON map.userId =  ud.id "
					+ "INNER JOIN RoleMaster rm ON map.roleId = rm.id "
					+ "ORDER BY ud.id", UserVO.class);
			return query.getResultList();
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
			return session.get(UserDetails.class, userId);
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

}
