package com.pvn.mvctiles.vo;

public class IssueVO
{
	
	public IssueVO()
	{}
	
	public IssueVO(Integer id, String name, String summary, String description, String issueType, Integer reporter, Integer assignee, Integer projectId, String reporterStr,
			String assigneeStr, String projectStr)
	{
		super();
		this.id = id;
		this.name = name;
		this.summary = summary;
		this.description = description;
		this.issueType = issueType;
		this.reporter = reporter;
		this.assignee = assignee;
		this.projectId = projectId;
		this.reporterStr = reporterStr;
		this.assigneeStr = assigneeStr;
		this.projectStr = projectStr;
	}

	private Integer	id;
	private String	name;
	private String	summary;
	private String	description;
	private String	issueType;
	
	private Integer	reporter;
	private Integer	assignee;
	private Integer	projectId;
	
	private String	reporterStr;
	private String	assigneeStr;
	private String	projectStr;
	
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
	
	public String getReporterStr()
	{
		return reporterStr;
	}
	
	public void setReporterStr(String reporterStr)
	{
		this.reporterStr = reporterStr;
	}
	
	public String getAssigneeStr()
	{
		return assigneeStr;
	}
	
	public void setAssigneeStr(String assigneeStr)
	{
		this.assigneeStr = assigneeStr;
	}
	
	public String getProjectStr()
	{
		return projectStr;
	}
	
	public void setProjectStr(String projectStr)
	{
		this.projectStr = projectStr;
	}
}
