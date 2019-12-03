package com.gmail.nlpraveennl.vo;

import com.gmail.nlpraveennl.model.IssueComment;

public class IssueCommentVO
{
	private Integer	id;
	private Integer	issueId;
	private Integer	userId;
	private String	message;
	private String  userStr;
	
	
	public IssueCommentVO()
	{}
	
	public IssueCommentVO(IssueComment issueComment, String userName)
	{
		this.id = issueComment.getId();
		this.issueId = issueComment.getIssueId();
		this.userId = issueComment.getUserId();
		this.message = issueComment.getMessage();
		this.userStr = userName;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getIssueId()
	{
		return issueId;
	}

	public void setIssueId(Integer issueId)
	{
		this.issueId = issueId;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getUserStr()
	{
		return userStr;
	}

	public void setUserStr(String userStr)
	{
		this.userStr = userStr;
	}
}
