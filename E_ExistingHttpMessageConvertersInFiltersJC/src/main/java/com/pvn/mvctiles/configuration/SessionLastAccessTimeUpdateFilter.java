package com.pvn.mvctiles.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.pvn.mvctiles.model.UserDetails;

public class SessionLastAccessTimeUpdateFilter implements Filter
{
	private ApplicationContext appContext;
	
	Logger OUT = LoggerFactory.getLogger(SessionLastAccessTimeUpdateFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		Filter.super.init(filterConfig);
		appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		System.out.println(appContext);
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		String reqURL = getFullURL(request);

		OUT.trace("Last access time update filter :: {} :: {}", session, reqURL);

		if (session != null)
		{
			session.setAttribute("lastAccessTime", System.currentTimeMillis());
		}
		
		if(reqURL.contains("MessageConverterTest"))
		{
			MyResponse myResp = new MyResponse("UUID", "500", "Null Pointer Exception");
			write(req, resp, myResp);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}
	
	private static String getFullURL(ServletRequest req)
	{
		HttpServletRequest request = (HttpServletRequest) req;
		StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
		String queryString = request.getQueryString();

		if (queryString == null)
		{
			return requestURL.toString();
		}
		else
		{
			return requestURL.append('?').append(queryString).toString();
		}
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void write(HttpServletRequest req, HttpServletResponse resp, MyResponse myResp) throws IOException
	{
		MyConverters myConverters = (MyConverters) appContext.getBean("myConverters");
		System.out.println(myConverters);
		
		System.out.println(myConverters.getConverters());
		System.out.println(myConverters.getConverters().size());
		
		String accept= req.getHeader("accept");

		for (HttpMessageConverter messageConverter : myConverters.getConverters())
		{
			if (messageConverter.canWrite(UserDetails.class, MediaType.valueOf(accept)))
			{
				HttpOutputMessage outputMessage = new ServletServerHttpResponse(resp);
				messageConverter.write(myResp, MediaType.valueOf(accept), outputMessage);
			}
		}
	}

}
