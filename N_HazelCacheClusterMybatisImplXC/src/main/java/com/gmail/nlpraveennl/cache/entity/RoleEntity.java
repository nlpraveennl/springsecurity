package com.gmail.nlpraveennl.cache.entity;

import java.io.Serializable;

public class RoleEntity implements Serializable
{
	private static final long serialVersionUID = 9218406408149193122L;
	
	private int id;
	private String name;
	
	
	public RoleEntity(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
