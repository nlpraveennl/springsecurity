package com.pvn.mvctiles.controller;

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

import com.pvn.mvctiles.model.RoleMaster;
import com.pvn.mvctiles.model.UserDetails;
import com.pvn.mvctiles.service.RoleService;
import com.pvn.mvctiles.service.UserService;

@Controller
public class UserController
{
	Logger			OUT	= LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService		userService;
	
	@Autowired
	RoleService		roleService;

	@RequestMapping("/app/admin/user-details")
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
		
		return "user-details";
	}
	
	@RequestMapping("/app/admin/add-user")
	public String addUser(Model model, @ModelAttribute("user") UserDetails userDetails, HttpServletRequest request)
	{
		UserDetails user = userService.addUser(userDetails);
		model.addAttribute("msg", "User "+user.getFirstName()+" added successfully");
		model.addAttribute("roleList", roleService.getAll());
		
		return "user-details";
	}
	
	@RequestMapping("/app/admin/list-user")
	public String listUser(Model model, HttpServletRequest request)
	{
		model.addAttribute("userList", userService.listUserVO());
		
		return "user-list";
	}
	
	@RequestMapping("/app/user/userdetails/{userId}")
	public String listUser(@PathVariable("userId") int userId, Model model, HttpServletRequest request)
	{
		List<RoleMaster> roleList = roleService.getAll();
		
		model.addAttribute("user", userService.getUser(userId));
		model.addAttribute("roleList", roleList);
		
		return "user-details";
	}
}
