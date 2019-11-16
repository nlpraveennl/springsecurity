package com.pvn.mvctiles.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvn.mvctiles.model.RoleMaster;

@RestController
public class MyRestController2
{
	@RequestMapping("/api/error2/nullpointerException")
	public RoleMaster getNullPointerException()         
	{
		throw new NullPointerException("Null pointer exception");
	}
	
	@RequestMapping("/api/error2/arithmeticException")
	public RoleMaster getArithmeticException()         
	{
		throw new ArithmeticException("Devide by zero");
	}
	
	@RequestMapping("/api/error2/exception")
	public RoleMaster getException() throws Exception         
	{
		throw new Exception("Excpetion");
	}
}
