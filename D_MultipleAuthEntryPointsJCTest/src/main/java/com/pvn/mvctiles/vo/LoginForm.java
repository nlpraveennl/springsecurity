package com.pvn.mvctiles.vo;


public class LoginForm
{
	private String	username;
	private String	password;
	private String	userType = "user";
	
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
	
	public String getUserType()
	{
		return userType;
	}
	
	public void setUserType(String userType)
	{
		this.userType = userType;
	}
	
	
}
