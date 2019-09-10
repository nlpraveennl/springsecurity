package com.pvn.mvctiles.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
	{
		UsernamePasswordAuthenticationToken authRequest = null;

	    if ("user".equals(request.getParameter("userType"))) 
	    {
	        authRequest = new UserUsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("password"));
	    }
	    else 
	    {
	        authRequest = new AdminUsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("password"));
	    }

	    setDetails(request, authRequest);

	    return super.getAuthenticationManager().authenticate(authRequest);
	}
}
