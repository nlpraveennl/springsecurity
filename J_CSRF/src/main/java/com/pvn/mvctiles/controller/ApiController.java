package com.pvn.mvctiles.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController
{
	@RequestMapping("/api/test1")
	public String test1(Model model, HttpServletRequest request)
	{
		return "API Tested!";
	}
	
	@RequestMapping("/api/test2")
	public String test2(Model model, HttpServletRequest request)
	{
		return "API Tested!";
	}
}
