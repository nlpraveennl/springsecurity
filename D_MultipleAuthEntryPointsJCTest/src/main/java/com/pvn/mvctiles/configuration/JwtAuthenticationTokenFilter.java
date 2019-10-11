package com.pvn.mvctiles.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException
	{
		System.out.println("((((((((((((()))))))))))))))");
		final String requestHeader = request.getHeader("Authorization");
		System.out.println(":::::::::::"+requestHeader);
		chain.doFilter(request, response);
	}
}