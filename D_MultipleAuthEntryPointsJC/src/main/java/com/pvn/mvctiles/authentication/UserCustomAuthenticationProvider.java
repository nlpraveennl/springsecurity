package com.pvn.mvctiles.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class UserCustomAuthenticationProvider implements AuthenticationProvider
{
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (username.equals("user") && password.equals("user@123#"))
		{
			List<GrantedAuthority> authorityList = new ArrayList<>();
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
			authorityList.add(authority);

			return new UserUsernamePasswordAuthenticationToken(username, password, authorityList);
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return authentication.equals(UserUsernamePasswordAuthenticationToken.class);
	}
}
