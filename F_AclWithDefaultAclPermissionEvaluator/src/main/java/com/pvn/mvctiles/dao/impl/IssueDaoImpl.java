package com.pvn.mvctiles.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pvn.mvctiles.dao.IssueDao;
import com.pvn.mvctiles.model.Issue;
import com.pvn.mvctiles.model.IssueComment;
import com.pvn.mvctiles.vo.IssueVO;

@Component
public class IssueDaoImpl implements IssueDao
{
	Logger			OUT	= LoggerFactory.getLogger(IssueDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;

	@Override
	public void addIssue(Issue issue)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.save(issue);
			tx.commit();
		}
		catch (Exception e)
		{
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
			OUT.error("Exception - {}", e);
		}
	}

	@Override
	public void modifyIssue(Issue issue)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.update(issue);
			tx.commit();
		}
		catch (Exception e)
		{
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
			OUT.error("Exception - {}", e);
		}
	}

	@Override
	public List<IssueVO> listIssue()
	{
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<IssueVO> query = session.createQuery(""
					+ "SELECT NEW com.pvn.mvctiles.vo.IssueVO(i.id, i.name, i.summary, i.description, i.issueType, i.reporter, i.assignee, i.projectId, udrep.userName, udasn.userName, proj.name)"
					+ "FROM Issue i "
					+ "INNER JOIN UserDetails udrep ON udrep.id = i.reporter "
					+ "INNER JOIN UserDetails udasn ON udasn.id = i.assignee "
					+ "INNER JOIN Project proj ON proj.id = i.projectId", IssueVO.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public List<IssueVO> listIssue(int projectId)
	{
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<IssueVO> query = session.createQuery(""
					+ "SELECT NEW com.pvn.mvctiles.vo.IssueVO(i.id, i.name, i.summary, i.description, i.issueType, i.reporter, i.assignee, i.projectId, udrep.userName, udasn.userName, proj.name)"
					+ "FROM Issue i "
					+ "INNER JOIN UserDetails udrep ON udrep.id = i.reporter "
					+ "INNER JOIN UserDetails udasn ON udasn.id = i.assignee "
					+ "INNER JOIN Project proj ON proj.id = i.projectId "
					+ "WHERE i.projectId="+projectId, IssueVO.class);
			return query.getResultList();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public IssueVO getIssue(int issueId)
	{
		try (Session session = sessionFactory.openSession();)
		{
			TypedQuery<IssueVO> query = session.createQuery(""
					+ "SELECT NEW com.pvn.mvctiles.vo.IssueVO(i.id, i.name, i.summary, i.description, i.issueType, i.reporter, i.assignee, i.projectId, udrep.userName, udasn.userName, proj.name)"
					+ "FROM Issue i "
					+ "INNER JOIN UserDetails udrep ON udrep.id = i.reporter "
					+ "INNER JOIN UserDetails udasn ON udasn.id = i.assignee "
					+ "INNER JOIN Project proj ON proj.id = i.projectId "
					+ "WHERE i.id="+issueId, IssueVO.class);
			return query.getSingleResult();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public List<IssueComment> getComments(int issueId)
	{
		try (Session session = sessionFactory.openSession();)
		{
			return session.createQuery("From IssueComment WHERE issueId="+issueId, IssueComment.class).list();
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			return null;
		}
	}

	@Override
	public void deleteIssueComment(int commentId)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.createQuery("Delete FROM IssueComment WHERE id="+commentId).executeUpdate();
			tx.commit();
		}
		catch (Exception e)
		{
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
			OUT.error("Exception - {}", e);
		}
	}

	@Override
	public void addIssueComment(IssueComment issueComment)
	{
		Transaction tx = null;
		try (Session session = sessionFactory.openSession();)
		{
			tx = session.getTransaction();
			tx.begin();
			session.save(issueComment);
			tx.commit();
		}
		catch (Exception e)
		{
			if(tx != null && tx.isActive())
			{
				tx.rollback();
			}
			OUT.error("Exception - {}", e);
		}
	}
}
