package com.gmail.nlpraveennl.exceptionhandling;

public class Error
{

	private int		code;
	private String	message;

	public Error()
	{
	}

	public Error(int code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public int getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}