package com.gmail.nlpraveennl.cache.entity;

import java.io.Serializable;

import com.gmail.nlpraveennl.vo.UserVO;

public class UserEntity implements Serializable
{

	private static final long serialVersionUID = 4516255544470166618L;
	
	private Integer	id;
	private String	firstName;
	private String	lastName;
	private String	userName;
	private String	email;
	private String	gender;
	private boolean	enabled;
	private String  roleIds;
	private String  roleStrs;

	public UserEntity()
	{}
	
	public UserEntity(UserVO vo)
	{
		this.id = vo.getId();
		this.firstName = vo.getFirstName();
		this.lastName = vo.getLastName();
		this.userName = vo.getUserName();
		this.email = vo.getEmail();
		this.gender = vo.getGender();
		this.enabled = vo.isEnabled();
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
	
	public String getRoleIds()
	{
		return roleIds;
	}
	
	public void setRoleIds(String roleIds)
	{
		this.roleIds = roleIds;
	}
	
	public String getRoleStrs()
	{
		return roleStrs;
	}
	
	public void setRoleStrs(String roleStrs)
	{
		this.roleStrs = roleStrs;
	}
}
