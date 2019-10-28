package com.gmail.nlpraveennl.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
	@RequestMapping(path = "/v1/hello")
	public String helloV1()
	{
		return "HELLO Version 1";
	}

	@RequestMapping(path = "/v0.9/hello")
	public String helloV0Dot9()
	{
		return "HELLO Version 0.9";
	}

	@RequestMapping(path = "/v0.8/hello")
	public String helloV0Dot8()
	{
		return "HELLO Version 0.8";
	}
}
