package com.pvn.mvctiles.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pvn.mvctiles.vo.LoginForm;

@Controller
public class AuthenticationController
{

	Logger			OUT	= LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(value = { "/customlogin", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(ModelAndView model, @ModelAttribute("login") LoginForm loginForm, 
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "expired", required = false) String expired)
	{
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
		return "dashboard";
	}
	
	@RequestMapping("/app/user/profile")
	public String myProfile(Model model, HttpServletRequest request)
	{
		return "profile";
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
	
	@RequestMapping("app/admin/user-management")
	public String userManagement(Model model, HttpServletRequest request)
	{
		return "admin/user-management";
	}
}
