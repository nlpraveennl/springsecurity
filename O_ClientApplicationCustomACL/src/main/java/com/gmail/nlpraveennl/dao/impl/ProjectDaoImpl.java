package com.gmail.nlpraveennl.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gmail.nlpraveennl.dao.ProjectDao;
import com.gmail.nlpraveennl.model.Project;
import com.gmail.nlpraveennl.vo.ProjectVO;

@Component
public class ProjectDaoImpl implements ProjectDao
{

	Logger			OUT	= LoggerFactory.getLogger(ProjectDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public void addProject(Project project)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.save(project);
			tx.commit();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
		}
	}

	@Override
	public void modifyProject(Project project)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.update(project);
			tx.commit();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
		}
	}
	
	@Override
	public ProjectVO getProject(int projectId)
	{
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<ProjectVO> query = session.createQuery("SELECT NEW com.gmail.nlpraveennl.vo.ProjectVO(p.id, p.name, p.description, p.mgr, ud.userName) "
					+ "FROM Project p INNER JOIN UserDetails ud ON ud.id = p.mgr "
					+ "WHERE p.id="+projectId, ProjectVO.class);
			return query.getSingleResult();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public List<ProjectVO> listProject()
	{
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<ProjectVO> query = session.createQuery("SELECT NEW com.gmail.nlpraveennl.vo.ProjectVO(p.id, p.name, p.description, p.mgr, ud.userName) FROM Project p INNER JOIN UserDetails ud ON ud.id = p.mgr", ProjectVO.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}
}
