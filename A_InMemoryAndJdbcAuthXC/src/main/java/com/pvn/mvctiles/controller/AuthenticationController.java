package com.pvn.mvctiles.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pvn.mvctiles.model.UserDetails;

@Controller
public class AuthenticationController
{

	Logger			OUT	= LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	MessageSource	messageSource;

	@RequestMapping(value = { "/login", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(ModelAndView model, @ModelAttribute("login") UserDetails userDetails, BindingResult result,
			@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "expired", required = false) String expired,HttpServletRequest request)
	{
		if (error != null)
		{
			model.addObject("error", "Invalid credentials");
		}

		if (logout != null)
		{
			model.addObject("msg", "Logout success");
		}
		
		if (expired != null)
		{
			model.addObject("msg", "Sesion Expired");
		}

		model.setViewName("login");
		return model;
	}

	@RequestMapping("/app/user/dashboard")
	public String showDashboard(Model model, HttpServletRequest request)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = new UserDetails();
		user.setUserName(((User)principal).getUsername());
		user.setPassword(((User)principal).getPassword());
		
		model.addAttribute("loggedInUser", user);
		request.getSession(false).setAttribute("loggedInUser", user);
		return "dashboard";
	}
	
	@RequestMapping("/403")
	public String accessDenied(Model model, HttpServletRequest request)
	{
		return "403";
	}
	
	@RequestMapping("app/admin/app-config")
	public String appConfig(Model model, HttpServletRequest request)
	{
		return "admin/app-config";
	}
	
	@RequestMapping("/keepSessionAlive")
	public @ResponseBody String sessionCheck(Model model, HttpServletRequest request)
	{
		return "session refreshed successfully";
	}
}
