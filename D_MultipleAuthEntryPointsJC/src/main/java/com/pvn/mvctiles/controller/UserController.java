package com.pvn.mvctiles.controller;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.pvn.mvctiles.configuration.MyResponse;
import com.pvn.mvctiles.model.RoleMaster;
import com.pvn.mvctiles.model.DbUserDetails;
import com.pvn.mvctiles.service.RoleService;
import com.pvn.mvctiles.service.UserService;
import com.pvn.mvctiles.validators.UserValidator;
import com.pvn.mvctiles.vo.ListWrapper;

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
		DbUserDetails user = new DbUserDetails();
		user.setGender("MALE");
		
		if(!roleList.isEmpty())
		{
			user.setRoleId(roleList.get(0).getId());
		}
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		
		return "user-form";
	}
	
	@RequestMapping(value = "/app/admin/add-users", method = RequestMethod.GET)
	public String addUsers(Model model, HttpServletRequest request)
	{
		List<RoleMaster> roleList = roleService.getAll();
		List<DbUserDetails> usersList = new ArrayList<>();
		
		ListWrapper userListWrapper = new ListWrapper();
		userListWrapper.setList(usersList);
		
		
		DbUserDetails user;
		for(int i=0; i<3;i++)
		{
			user = new DbUserDetails();
			user.setGender("MALE");
			user.setRoleId(roleList.get(1).getId());
			
			usersList.add(user);
		}
		
		
		model.addAttribute("userListWrapper", userListWrapper);
		model.addAttribute("roleList", roleList);
		
		return "add-users";
	}
	
	@RequestMapping(value = "/app/admin/add-users", method = RequestMethod.POST)
	public String saveUsers(@ModelAttribute("userListWrapper") ListWrapper userListWrapper, Model model, HttpServletRequest request)
	{
		List<DbUserDetails> usersList = userListWrapper.getList();
		Iterator<DbUserDetails> itr = usersList.iterator();
		
		while(itr.hasNext())
		{
			if(itr.next().getFirstName() == null)
			{
				itr.remove();
			}
		}
		
		userListWrapper.getList().forEach(user -> {
			System.out.println(user.getFirstName());
		});
		return "add-users";
	}
	
	@RequestMapping("/app/admin/add-user")
	public String addUser(Model model, @ModelAttribute("user") DbUserDetails userDetails, HttpServletRequest request)
	{
		DbUserDetails user = userService.addUser(userDetails);
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
