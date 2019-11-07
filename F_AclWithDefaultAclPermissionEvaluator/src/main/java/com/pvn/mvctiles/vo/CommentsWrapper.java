package com.pvn.mvctiles.vo;

import java.util.List;

public class CommentsWrapper
{
	private List<IssueCommentVO> commentList;

	
	public List<IssueCommentVO> getCommentList()
	{
		return commentList;
	}

	
	public void setCommentList(List<IssueCommentVO> commentList)
	{
		this.commentList = commentList;
	}
}
