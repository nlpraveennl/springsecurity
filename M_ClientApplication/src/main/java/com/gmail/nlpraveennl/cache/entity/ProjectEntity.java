package com.gmail.nlpraveennl.cache.entity;

import java.io.Serializable;

public class ProjectEntity implements Serializable
{
	
	private static final long serialVersionUID = -2964851928066592408L;
	
	private Integer	id;
	private String	name;
	private String	description;
	private Integer	mgr;
	private String	mgrStr;
	
	public ProjectEntity()
	{}
	
	
	public ProjectEntity(Integer id, String name, String description, Integer mgr, String mgrStr)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.mgr = mgr;
		this.mgrStr = mgrStr;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
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
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Integer getMgr()
	{
		return mgr;
	}
	
	public void setMgr(Integer mgr)
	{
		this.mgr = mgr;
	}
	
	public String getMgrStr()
	{
		return mgrStr;
	}
	
	public void setMgrStr(String mgrStr)
	{
		this.mgrStr = mgrStr;
	}
}
