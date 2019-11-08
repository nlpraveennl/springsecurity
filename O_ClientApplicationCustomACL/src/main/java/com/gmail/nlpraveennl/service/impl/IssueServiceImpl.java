package com.gmail.nlpraveennl.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.dao.IssueDao;
import com.gmail.nlpraveennl.model.Issue;
import com.gmail.nlpraveennl.model.IssueComment;
import com.gmail.nlpraveennl.model.UserDetails;
import com.gmail.nlpraveennl.service.IssueService;
import com.gmail.nlpraveennl.vo.IssueCommentVO;
import com.gmail.nlpraveennl.vo.IssueVO;

@Service
public class IssueServiceImpl implements IssueService
{

	Logger	OUT	= LoggerFactory.getLogger(IssueServiceImpl.class);
	
	@Autowired
	private IssueDao issueDao;

	@Override
	public void addIssue(Issue issue)
	{
		issueDao.addIssue(issue);
	}

	@Override
	public void modifyIssue(Issue issue)
	{
		issueDao.modifyIssue(issue);
	}

	@Override
	public List<IssueVO> listIssue()
	{
		return issueDao.listIssue();
	}

	@Override
	public List<IssueVO> listIssue(int projectId)
	{
		return issueDao.listIssue(projectId);
	}

	@Override
	public IssueVO getIssue(int issueId)
	{
		return issueDao.getIssue(issueId);
	}

	@Override
	public List<IssueComment> getComments(int issueId)
	{
		return issueDao.getComments(issueId);
	}

	@Override
	public void deleteIssueComment(int commentId)
	{
		issueDao.deleteIssueComment(commentId);
	}

	@Override
	public List<IssueCommentVO> getCommentVOs(int issueId,List<UserDetails> users)
	{
		List<IssueComment> comments = issueDao.getComments(issueId);
		List<IssueCommentVO> commentVOs = new ArrayList<>();
		Map<Integer, String> userMap = new HashMap<>();
		users.forEach(user -> userMap.put(user.getId() , user.getUserName()));
		
		comments.forEach(comment -> {
			commentVOs.add(new IssueCommentVO(comment, userMap.get(comment.getUserId())));
		});
		
		return commentVOs;
	}

	@Override
	public void addIssueComment(IssueComment issueComment)
	{
		issueDao.addIssueComment(issueComment);
	}
}
