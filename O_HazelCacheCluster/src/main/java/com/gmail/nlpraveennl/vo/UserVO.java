package com.gmail.nlpraveennl.vo;

import java.io.Serializable;

public class UserVO implements Serializable
{

	private static final long serialVersionUID = 4516255544470166618L;
	
	private Integer	id;
	private String	firstName;
	private String	lastName;
	private String	userName;
	private String	email;
	private String	gender;
	private boolean	enabled;
	private Integer	roleId;
	private String	roleStr;

	public UserVO()
	{}
	
	public UserVO(Integer id, String firstName, String lastName, String userName, String email, String gender, boolean enabled, Integer roleId, String roleStr)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.gender = gender;
		this.enabled = enabled;
		this.roleId = roleId;
		this.roleStr = roleStr;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleStr()
	{
		return roleStr;
	}

	public void setRoleStr(String roleStr)
	{
		this.roleStr = roleStr;
	}
}
