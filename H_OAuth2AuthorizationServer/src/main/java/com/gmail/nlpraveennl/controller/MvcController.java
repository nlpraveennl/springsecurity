package com.gmail.nlpraveennl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gmail.nlpraveennl.vo.TokenRequest;

@Controller
public class MvcController
{

	Logger			OUT	= LoggerFactory.getLogger(MvcController.class);

	@RequestMapping(value = {"/"})
	public String showDashboard(Model model, HttpServletRequest request)
	{
		return "dashboard";
	}
	
	@RequestMapping(value = {"/receivecode"},  method = RequestMethod.GET)
	public String receivecode(@ModelAttribute TokenRequest token, Model model, HttpServletRequest request, @RequestParam("code") String code)
	{
		token.setCode(code);
		token.setGrant_type("authorization_code");
		token.setRedirect_uri("http://localhost:8080/spring-security-oauth2-authorization-server/receivecode");
		model.addAttribute("token", token);
		return "receivecode";
	}
}
