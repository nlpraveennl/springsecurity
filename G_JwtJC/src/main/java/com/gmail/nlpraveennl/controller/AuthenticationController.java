package com.gmail.nlpraveennl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController
{

	Logger			OUT	= LoggerFactory.getLogger(AuthenticationController.class);

	@RequestMapping(value = {"/"})
	public String showDashboard(Model model, HttpServletRequest request)
	{
		return "dashboard";
	}
}
