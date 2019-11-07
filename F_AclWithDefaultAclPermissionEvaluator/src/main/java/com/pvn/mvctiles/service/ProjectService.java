package com.pvn.mvctiles.service;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.pvn.mvctiles.model.Project;
import com.pvn.mvctiles.vo.ProjectVO;

public interface ProjectService
{
	
	/**
	 * @param project
	 */
	public void addProject(Project project);
	
	/**
	 * @param projectId
	 * @return
	 */
	public ProjectVO getProject(int projectId);
	
	/**
	 * @return
	 */
	@PostFilter("hasPermission(filterObject, 'READ')")
	public List<ProjectVO> listProject();
	
	/**
	 * @param project
	 */
	@PreAuthorize("hasPermission(#project, 'WRITE')")
	public void modifyProject(Project project);
}
