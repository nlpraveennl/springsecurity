package com.pvn.mvctiles.configuration;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.pvn.mvctiles.model.UserDetails;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException 
	{
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ROLE_ADMIN"))
        {
        	request.getSession(false).setMaxInactiveInterval(60);
        }
        else
        {
        	request.getSession(false).setMaxInactiveInterval(120);
        }
        
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = new UserDetails();
		user.setUserName(((User)principal).getUsername());
		user.setPassword(((User)principal).getPassword());
		
		request.getSession(false).setAttribute("loggedInUser", user);
		
		response.sendRedirect(request.getContextPath());
    }
}