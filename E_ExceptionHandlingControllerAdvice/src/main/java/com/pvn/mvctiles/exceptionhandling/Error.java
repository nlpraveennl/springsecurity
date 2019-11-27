package com.pvn.mvctiles.exceptionhandling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Error")
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

	@XmlElement
	public int getCode()
	{
		return code;
	}

	@XmlElement
	public String getMessage()
	{
		return message;
	}

	
	public void setCode(int code)
	{
		this.code = code;
	}

	
	public void setMessage(String message)
	{
		this.message = message;
	}
}