package com.gmail.nlpraveennl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role_mapping")
public class UserRoleMapping implements Serializable
{
	private static final long serialVersionUID = -3719618727227768853L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	private int roleId;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	public int getRoleId()
	{
		return roleId;
	}
	
	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(id);
		builder.append(":");
		builder.append(userId);
		builder.append(":");
		builder.append(roleId);
		return builder.toString();
	}
	
	
}
