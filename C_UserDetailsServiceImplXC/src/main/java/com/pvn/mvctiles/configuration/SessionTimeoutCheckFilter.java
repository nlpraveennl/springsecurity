package com.pvn.mvctiles.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionTimeoutCheckFilter implements Filter
{

	Logger OUT = LoggerFactory.getLogger(SessionTimeoutCheckFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setStatus(HttpServletResponse.SC_OK);
		
		if (session != null && session.getAttribute("lastAccessTime") != null)
		{
			Long lastAccessTime = (Long) session.getAttribute("lastAccessTime");
			Long idleTimeInSeconds = (System.currentTimeMillis() - lastAccessTime) / 1000;
			Long timeLeft = session.getMaxInactiveInterval() - idleTimeInSeconds;
			
			if (timeLeft <= 0)
			{
				OUT.trace("Session Expired. Session Id: {}", session.getId());
				resp.getWriter().print(timeLeft);
			}
			else
			{
				OUT.trace("Session valid. Time left: {}", timeLeft);
				resp.getWriter().print(timeLeft);
			}
		}
		else
		{
			OUT.warn("Request received without a valid session");
			resp.getWriter().print(-1);
		}
	}

}
