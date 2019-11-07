package com.pvn.mvctiles.service;

import java.util.List;

import com.pvn.mvctiles.model.Issue;
import com.pvn.mvctiles.model.IssueComment;
import com.pvn.mvctiles.model.UserDetails;
import com.pvn.mvctiles.vo.IssueCommentVO;
import com.pvn.mvctiles.vo.IssueVO;

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
