package com.gmail.nlpraveennl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.nlpraveennl.exceptionhandling.Error;
import com.gmail.nlpraveennl.exceptionhandling.UserNotFoundException;
import com.gmail.nlpraveennl.model.RoleMaster;
import com.gmail.nlpraveennl.model.UserDetails;
import com.gmail.nlpraveennl.service.UserService;

@RestController
public class MyRestController1
{
	@Autowired
	UserService userService;
	
	@ExceptionHandler(NullPointerException.class)
	public Error sendNullPointerResponse()
	{
		Error error = new Error(404, "NullPointerException caught");
		return error;
	}
	
	@ExceptionHandler(Exception.class)
	public Error sendExceptionResponse()
	{
		Error error = new Error(500, "Exception caught");
		return error;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public Error sendEmpNotFoundResponse()
	{
		Error error = new Error(404, "Employee not found");
		return error;
	}
	
	
	@RequestMapping("/api/messageConverterTest/role")
	public RoleMaster httpMessageConversionTest()
	{
		RoleMaster role = new RoleMaster();
		role.setId(1);
		role.setName("Admin");
		
		return role;
	}
	
	@RequestMapping("/api/error1/nullpointerException")
	public RoleMaster getNullPointerException()         
	{
		throw new NullPointerException();
	}
	
	@RequestMapping("/api/error1/arithmeticException")
	public RoleMaster getArithmeticException()         
	{
		throw new ArithmeticException("Devide by zero");
	}
	
	@RequestMapping("/api/error1/exception")
	public RoleMaster getException() throws Exception         
	{
		throw new Exception();
	}
	
	@RequestMapping("/api/user/{userId}")
	public UserDetails getEmployee(@PathVariable("userId") int userId) throws Exception         
	{
		UserDetails user = userService.getUser(userId);
		if(user != null)
		{
			return user;
		}
		else
		{
			throw new UserNotFoundException(userId);
		}
	}
}
