package com.gmail.nlpraveennl.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmail.nlpraveennl.model.Project;
import com.gmail.nlpraveennl.model.UserDetails;
import com.gmail.nlpraveennl.service.ProjectService;
import com.gmail.nlpraveennl.service.UserService;
import com.gmail.nlpraveennl.vo.ProjectVO;

@Controller
public class ProjectController
{
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/app/user/projects-list")
	public String getAllProjects(Model model, HttpServletRequest request)
	{
		List<ProjectVO> list = projectService.listProject();
		model.addAttribute("projects", list);
		
		return "projects-list";
	}
	
	@RequestMapping("/app/user/project-details")
	public String preAdd(Model model, HttpServletRequest request)
	{
		model.addAttribute("project", new Project());
		model.addAttribute("users", userService.listUser());
		
		return "project-details";
	}
	
	@RequestMapping("/app/user/project/{projectId}")
	public String preModify(@PathVariable("projectId") int projectId, Model model, HttpServletRequest request)
	{
		model.addAttribute("project", projectService.getProject(projectId));
		model.addAttribute("users", userService.listUser());
		model.addAttribute("update", true);
		
		return "project-details";
	}
	
	@RequestMapping("/app/user/add-project")
	public String addProject(@ModelAttribute("project") Project project, Model model, HttpServletRequest request)
	{
		projectService.addProject(project);
		
		model.addAttribute("projects", projectService.listProject());
		return "projects-list";
	}
	
	@RequestMapping("/app/user/modify-project")
	public String modifyProject(@ModelAttribute("project") Project project, Model model, HttpServletRequest request)
	{
		projectService.modifyProject(project);
		
		model.addAttribute("projects", projectService.listProject());
		List<String> x = userService.listUser().stream().map(UserDetails::getUserName).collect(Collectors.toList());
		model.addAttribute("users", x);
		
		return "projects-list";
	}
}
