package com.pvn.mvctiles.vo;
//https://medium.com/sfl-newsroom/spring-security-expression-based-access-control-56411a60ab3b
//https://stackoverflow.com/questions/26549389/when-should-i-implement-spring-security-acl-in-my-application
public class ProjectVO
{
 
	private Integer	id;
	private String	name;
	private String	description;
	private Integer	mgr;
	private String	mgrStr;
	
	public ProjectVO()
	{}
	
	
	public ProjectVO(Integer id, String name, String description, Integer mgr, String mgrStr)
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
