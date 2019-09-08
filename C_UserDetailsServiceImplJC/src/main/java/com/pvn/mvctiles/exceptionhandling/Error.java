package com.pvn.mvctiles.exceptionhandling;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
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