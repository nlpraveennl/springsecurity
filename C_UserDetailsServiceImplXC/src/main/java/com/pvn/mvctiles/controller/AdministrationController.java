package com.pvn.mvctiles.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pvn.mvctiles.service.UserService;
import com.pvn.mvctiles.validators.UserValidator;

@Controller
public class AdministrationController
{
	Logger			OUT	= LoggerFactory.getLogger(AdministrationController.class);

	@Autowired
	UserValidator	validator;

	@Autowired
	UserService		userService;

	@RequestMapping("/app/admin/app-config")
	public String showDashboard(Model model, HttpServletRequest request)
	{
		return "app-config";
	}
}
