package com.gmail.nlpraveennl.dao;

import java.util.List;

import com.gmail.nlpraveennl.model.Project;
import com.gmail.nlpraveennl.vo.ProjectVO;

public interface ProjectDao
{
	
	/**
	 * @param project
	 */
	public void addProject(Project project);
	
	/**
	 * @param project
	 */
	public void modifyProject(Project project);
	
	/**
	 * @param projectId
	 * @return
	 */
	public ProjectVO getProject(int projectId);
	
	/**
	 * @return
	 */
	public List<ProjectVO> listProject();
}
