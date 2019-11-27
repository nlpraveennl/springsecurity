package com.pvn.mvctiles.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoggerIntercepter extends HandlerInterceptorAdapter
{

	private static final Logger	OUT			= LoggerFactory.getLogger(LoggerIntercepter.class);

	private String				threadName	= "";
	private long				startTime;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		startTime = System.currentTimeMillis();
		threadName = Thread.currentThread().getName();
		Thread.currentThread().setName(threadName + "[ " + getRemoteAddr(request) + " ]");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		OUT.info("Request URL: {} Completed in {} ms,  view-name: {}", request.getRequestURI(), (System.currentTimeMillis() - startTime),
				modelAndView != null ? modelAndView.getViewName()+".jsp" : "no view");
		startTime = System.currentTimeMillis();
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		OUT.info("Request URL: {} View completed in {} ms", request.getRequestURI(), (System.currentTimeMillis() - startTime));
		Thread.currentThread().setName(threadName);
		super.afterCompletion(request, response, handler, ex);
	}

	private String getRemoteAddr(HttpServletRequest request)
	{
		String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
		if (ipFromHeader != null && ipFromHeader.length() > 0)
		{
			return "X-FORWARDED-FOR: " + ipFromHeader;
		}
		return request.getRemoteAddr();
	}
}
