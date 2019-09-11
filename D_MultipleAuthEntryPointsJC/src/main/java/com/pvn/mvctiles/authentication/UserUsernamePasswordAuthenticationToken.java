package com.pvn.mvctiles.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken
{
	private static final long serialVersionUID = -6897334548086628577L;

	public UserUsernamePasswordAuthenticationToken(Object principal, Object credentials)
	{
		super(principal, credentials);
	}
	
	public UserUsernamePasswordAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities)
	{
		super(principal, credentials, authorities);
	}
}

