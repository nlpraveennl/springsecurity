package com.pvn.mvctiles.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pvn.mvctiles.exceptionhandling.Error;

@Controller
@RequestMapping(value = "/unsecured")
public class ContentTypeTestController
{
	@RequestMapping(value = { "/consume-json" }, method = {RequestMethod.GET, RequestMethod.POST},
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Error consumeJSON(@RequestBody Error input, Model model, HttpServletRequest request)
	{
		Error error = new Error(200, "JSON Consume Test"+ input.getMessage());
		return error;
	}
	
	@RequestMapping(value = { "/consume-xml" }, method = {RequestMethod.GET, RequestMethod.POST},
			consumes = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Error consumeXML(@RequestBody Error input, Model model, HttpServletRequest request)
	{
		Error error = new Error(200, "JSON Consume Test"+ input.getMessage());
		return error;
	}
	
	@RequestMapping(value = { "/consume-any" }, method = {RequestMethod.GET, RequestMethod.POST},
					consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
					produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody Error consumeAny(@RequestBody Error input, Model model, HttpServletRequest request)
	{
		Error error = new Error(200 + input.getCode(), "JSON Consume/produces test. "+ input.getMessage());
		return error;
	}
	
	@RequestMapping(value = { "/produces-json" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody Error producesJSON(Model model, HttpServletRequest request)
	{
		Error error = new Error(200, "JSON Produces Test");
		return error;
	}
	
	@RequestMapping(value = { "/produces-xml" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Error producesXML(Model model, HttpServletRequest request)
	{
		Error error = new Error(200, "XML Produces Test");
		return error;
	}
}
