package com.gmail.nlpraveennl.service;

import java.util.List;

import com.gmail.nlpraveennl.model.Issue;
import com.gmail.nlpraveennl.model.IssueComment;
import com.gmail.nlpraveennl.model.UserDetails;
import com.gmail.nlpraveennl.vo.IssueCommentVO;
import com.gmail.nlpraveennl.vo.IssueVO;

public interface IssueService
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
	 * @param issueId 
	 * @param users
	 * @return
	 */
	public List<IssueCommentVO> getCommentVOs(int issueId, List<UserDetails> users);

	/**
	 * @param issueComment
	 */
	public void addIssueComment(IssueComment issueComment);
}
