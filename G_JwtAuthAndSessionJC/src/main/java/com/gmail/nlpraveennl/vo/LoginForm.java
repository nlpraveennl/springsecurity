package com.gmail.nlpraveennl.vo;

import java.io.Serializable;

public class LoginForm implements Serializable
{
	private static final long serialVersionUID = 2096338176073120753L;
	
	private String	username;
	private String	password;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String userName)
	{
		this.username = userName;
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