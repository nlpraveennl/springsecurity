package com.pvn.mvctiles.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AdminUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken
{
	private static final long serialVersionUID = 991224964095940527L;
	
	public AdminUsernamePasswordAuthenticationToken(Object principal, Object credentials)
	{
		super(principal, credentials);
	}
	
	public AdminUsernamePasswordAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities)
	{
		super(principal, credentials, authorities);
	}
}

