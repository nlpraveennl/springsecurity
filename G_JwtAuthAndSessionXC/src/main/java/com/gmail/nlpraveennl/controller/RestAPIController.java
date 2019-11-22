package com.gmail.nlpraveennl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.nlpraveennl.util.JwtTokenUtil;
import com.gmail.nlpraveennl.vo.LoginForm;

@RestController
public class RestAPIController
{
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping("/api/hi")
	public String hi()
	{
		return "hi";
	}
	
	@RequestMapping("/api/hello")
	public String hello()
	{
		return "hello";
	}
	
	@RequestMapping(value="/api/authenticate")
	public String hello(@RequestBody LoginForm form)
	{
		if(form.getUsername().equals("apiuser") && form.getPassword().equals("apiuser@123#")) //or you can use your service to authenticate user
		{
			return jwtTokenUtil.generateToken(form.getUsername());
		}
		else
		{
			return "INVALID CREDENTIAL";
		}
	}
}
