package com.pvn.mvctiles.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController1
{
	
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

}
