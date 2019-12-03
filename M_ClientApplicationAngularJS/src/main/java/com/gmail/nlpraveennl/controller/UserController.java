package com.gmail.nlpraveennl.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmail.nlpraveennl.model.RoleMaster;
import com.gmail.nlpraveennl.model.UserDetails;
import com.gmail.nlpraveennl.service.RoleService;
import com.gmail.nlpraveennl.service.UserService;
import com.gmail.nlpraveennl.vo.UserVO;

@Controller
public class UserController
{

	Logger				OUT	= LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService			userService;

	@Autowired
	RoleService			roleService;

	@RequestMapping("/app/admin/user-details")
	public String showUserForm(Model model, HttpServletRequest request)
	{
		List<RoleMaster> roleList = roleService.getAll();
		
		UserDetails user = new UserDetails();
		user.setGender("MALE");
		
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
	
	@RequestMapping("/app/admin/modify-user")
	public String modifyUser(Model model, @ModelAttribute("user") UserDetails userDetails, HttpServletRequest request)
	{
		System.out.println("Previous mapping: "+ userDetails.getPreviousMappings());
		System.out.println("Selected Roles: "+userDetails.getSelectedRoles());
		UserDetails user = userService.modifyUser(userDetails);
		
		model.addAttribute("msg", "User "+user.getFirstName()+" modified successfully");
		model.addAttribute("roleList", roleService.getAll());
		
		return "redirect:/app/user/userdetails/"+userDetails.getId();
	}
	
	@RequestMapping("/app/admin/list-user")
	public String listUser(Model model, HttpServletRequest request)
	{
		model.addAttribute("userList", userService.listUserVO());
		return "user-list";
	}
	
	@RequestMapping("/app/admin/list-users")
	public @ResponseBody List<UserVO> listUsers(Model model, HttpServletRequest request)
	{
		return userService.listUserVO();
	}
	
	@RequestMapping("/app/user/userdetails/{userId}")
	public String listUser(@PathVariable("userId") int userId, Model model, HttpServletRequest request)
	{
		List<RoleMaster> roleList = roleService.getAll();
		
		model.addAttribute("user", userService.getUser(userId));
		model.addAttribute("roleList", roleList);
		model.addAttribute("update", true);
		
		return "user-details";
	}
	
	@RequestMapping("/app/user/mydetails")
	public @ResponseBody UserDetails myDetails(Model model, HttpServletRequest request)
	{
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails user = userService.getMyDetails(userName);
		return user;
	}
}
