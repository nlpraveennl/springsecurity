package com.gmail.nlpraveennl.dao;

import java.util.List;

import com.gmail.nlpraveennl.model.Issue;
import com.gmail.nlpraveennl.model.IssueComment;
import com.gmail.nlpraveennl.vo.IssueVO;

public interface IssueDao
{

	/**
	 * @param issue
	 */
	public void addIssue(Issue issue);

	/**
	 * @param issue
	 */
	public void modifyIssue(Issue issue);

	/**
	 * @return
	 */
	public List<IssueVO> listIssue();

	/**
	 * @param projectId
	 * @return
	 */
	public List<IssueVO> listIssue(int projectId);

	/**
	 * @param issueId
	 * @return
	 */
	public IssueVO getIssue(int issueId);

	/**
	 * @param issueId
	 * @return
	 */
	public List<IssueComment> getComments(int issueId);

	/**
	 * @param commentId
	 */
	public void deleteIssueComment(int commentId);

	/**
	 * @param issueComment
	 */
	public void addIssueComment(IssueComment issueComment);
}
