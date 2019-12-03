package com.gmail.nlpraveennl.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project implements Serializable
{
	private static final long serialVersionUID = 6391189570105469695L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;
	private String	name;
	private String	description;
	private Integer	mgr;

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
}
