package com.pvn.mvctiles.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.pvn.mvctiles.dao.UserDao;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	@Autowired
	private UserDao userDao;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		List<GrantedAuthority> authorityList = userDao.authenticate(username, password);
		
		if (!authorityList.isEmpty())
		{
			return new UsernamePasswordAuthenticationToken(username, password, authorityList);
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}