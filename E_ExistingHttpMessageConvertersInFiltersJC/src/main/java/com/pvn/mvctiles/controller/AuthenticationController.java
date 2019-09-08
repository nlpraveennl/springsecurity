package com.pvn.mvctiles.controller;

import java.util.Locale;

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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.pvn.mvctiles.model.UserDetails;
import com.pvn.mvctiles.service.UserService;
import com.pvn.mvctiles.validators.UserValidator;

@Controller
public class AuthenticationController
{

	Logger			OUT	= LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	UserValidator	validator;

	@Autowired
	MessageSource	messageSource;

	@Autowired
	UserService		userService;
	
	@Autowired
	LocaleResolver localeResolver;

	@RequestMapping(value = { "/login", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(ModelAndView model, @ModelAttribute("login") UserDetails userDetails, BindingResult result,
			@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "expired", required = false) String expired,HttpServletRequest request)
	{
		Locale locale = localeResolver.resolveLocale(request);
		
		if (error != null)
		{
			OUT.info("Invalid username and password!");
			model.addObject("error", messageSource.getMessage("app.login.invalid.credntials", null, "Invalid credentials", locale));
		}

		if (logout != null)
		{
			OUT.info("You've been logged out successfully.");
			model.addObject("msg", messageSource.getMessage("app.login.logout.success", null, "Logout success", locale));
		}
		
		if (expired != null)
		{
			OUT.info("Session has been expired.");
			model.addObject("msg", messageSource.getMessage("app.login.session.expired", null, "Sesion Expired", locale));
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
	
	@RequestMapping("/keepSessionAlive")
	public @ResponseBody String sessionCheck(Model model, HttpServletRequest request)
	{
		return "session refreshed successfully";
	}
}
