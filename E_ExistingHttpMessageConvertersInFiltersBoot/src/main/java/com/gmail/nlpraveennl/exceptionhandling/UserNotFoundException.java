package com.gmail.nlpraveennl.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not Found") // 404
public class UserNotFoundException extends Exception
{

	private static final long serialVersionUID = -3332292346834265371L;

	public UserNotFoundException(int id)
	{
		super("UserNotFoundException with id=" + id);
	}
}