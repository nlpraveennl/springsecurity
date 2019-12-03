package com.gmail.nlpraveennl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.nlpraveennl.dao.ProjectDao;
import com.gmail.nlpraveennl.model.Project;
import com.gmail.nlpraveennl.service.ProjectService;
import com.gmail.nlpraveennl.vo.ProjectVO;

@Service
public class ProjectServiceImpl implements ProjectService
{

	Logger	OUT	= LoggerFactory.getLogger(ProjectServiceImpl.class);
	
	@Autowired
	private ProjectDao projectDao;

	@Override
	public void addProject(Project project)
	{
		projectDao.addProject(project);
	}

	@Override
	public void modifyProject(Project project)
	{
		projectDao.modifyProject(project);
	}
	
	@Override
	public ProjectVO getProject(int projectId)
	{
		return projectDao.getProject(projectId);
	}

	@Override
	public List<ProjectVO> listProject()
	{
		return projectDao.listProject();
	}
}
