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

import com.gmail.nlpraveennl.dao.UserDao;
import com.gmail.nlpraveennl.vo.UserVO;

@Component
public class UserDaoImpl implements UserDao
{

	Logger			OUT	= LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public List<UserVO> getAll(Collection<Integer> keys)
	{
		String commaSeparatedKeys = keys.stream().map(String::valueOf).collect(Collectors.joining(","));
		try (Session session = sessionFactory.openSession();)
		{
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT NEW com.gmail.nlpraveennl.vo.UserVO(ud.id, ud.firstName, ud.lastName, ud.userName, ud.email, ud.gender, ud.enabled, rm.id, rm.name) ")
					.append("FROM UserDetails ud INNER JOIN UserRoleMapping map ON map.userId =  ud.id ")
					.append("INNER JOIN RoleMaster rm ON map.roleId = rm.id ")
					.append("WHERE ud.id IN(").append(commaSeparatedKeys).append(") ")
					.append("ORDER BY ud.id");
			
			TypedQuery<UserVO> query = session.createQuery(builder.toString(), UserVO.class);
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
			TypedQuery<Integer> query = session.createQuery("SELECT id FROM UserDetails", Integer.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

}