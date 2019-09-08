package com.pvn.mvctiles.model;

import java.io.Serializable;

public class UserDetails implements Serializable
{

	private static final long	serialVersionUID	= -8203064310467718429L;

	private String				userName;
	private String				password;

	public UserDetails()
	{}

	public UserDetails(String userName, String password)
	{
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
