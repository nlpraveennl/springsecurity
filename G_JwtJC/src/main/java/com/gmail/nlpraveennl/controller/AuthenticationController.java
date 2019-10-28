package com.gmail.nlpraveennl.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name, HttpServletRequest request)
	{
		System.out.println("-----------------------------------------");
		System.out.println("Request URL: "+request.getRequestURL());
		System.out.println("name: "+name);
        return "Hello " + name;
    }
}
