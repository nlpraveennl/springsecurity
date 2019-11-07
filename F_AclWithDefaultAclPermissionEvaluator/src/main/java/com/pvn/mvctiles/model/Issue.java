package com.pvn.mvctiles.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issue")
public class Issue implements Serializable
{
	private static final long serialVersionUID = -1046059783338486571L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	id;
	private String	name;
	private String	summary;
	private String	description;
	private String	issueType;
	private Integer	reporter;
	private Integer	assignee;
	private Integer	projectId;

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

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getIssueType()
	{
		return issueType;
	}

	public void setIssueType(String issueType)
	{
		this.issueType = issueType;
	}

	public Integer getReporter()
	{
		return reporter;
	}

	public void setReporter(Integer reporter)
	{
		this.reporter = reporter;
	}

	public Integer getAssignee()
	{
		return assignee;
	}

	public void setAssignee(Integer assignee)
	{
		this.assignee = assignee;
	}

	public Integer getProjectId()
	{
		return projectId;
	}

	public void setProjectId(Integer projectId)
	{
		this.projectId = projectId;
	}
}
