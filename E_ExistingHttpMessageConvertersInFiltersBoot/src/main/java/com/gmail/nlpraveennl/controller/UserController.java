package com.gmail.nlpraveennl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmail.nlpraveennl.configuration.MyResponse;
import com.gmail.nlpraveennl.model.RoleMaster;
import com.gmail.nlpraveennl.model.UserDetails;
import com.gmail.nlpraveennl.service.RoleService;
import com.gmail.nlpraveennl.service.UserService;
import com.gmail.nlpraveennl.validators.UserValidator;

@Controller
public class UserController
{
	Logger			OUT	= LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserValidator	validator;

	@Autowired
	UserService		userService;
	
	@Autowired
	RoleService		roleService;

	@RequestMapping("/app/admin/user-form")
	public String showUserForm(Model model, HttpServletRequest request)
	{
		List<RoleMaster> roleList = roleService.getAll();
		UserDetails user = new UserDetails();
		user.setGender("MALE");
		
		if(!roleList.isEmpty())
		{
			user.setRoleId(roleList.get(0).getId());
		}
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		
		return "user-form";
	}
	
	@RequestMapping("/app/admin/add-user")
	public String addUser(Model model, @ModelAttribute("user") UserDetails userDetails, HttpServletRequest request)
	{
		UserDetails user = userService.addUser(userDetails);
		model.addAttribute("msg", "User "+user.getFirstName()+" added successfully");
		model.addAttribute("roleList", roleService.getAll());
		
		return "user-form";
	}
	
	@RequestMapping("/app/admin/list-user")
	public String listUser(Model model, HttpServletRequest request)
	{
		model.addAttribute("userList", userService.listUser());
		
		return "user-list";
	}
	
	@RequestMapping(value = "/sendError/{errorMessage}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody MyResponse sendError(@PathVariable String errorMessage)
	{
		return new MyResponse("UUID-1","503",errorMessage);
	}
}
