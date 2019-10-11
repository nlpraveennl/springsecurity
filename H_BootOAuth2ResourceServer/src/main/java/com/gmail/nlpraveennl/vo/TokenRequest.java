package com.gmail.nlpraveennl.vo;

import java.io.Serializable;

public class TokenRequest implements Serializable
{
	private static final long serialVersionUID = 3277129400813031748L;
	
	private String  grant_type;
	private String  code;
	private String  redirect_uri;
	
	public String getGrant_type()
	{
		return grant_type;
	}
	
	public void setGrant_type(String grant_type)
	{
		this.grant_type = grant_type;
	}
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public String getRedirect_uri()
	{
		return redirect_uri;
	}
	
	public void setRedirect_uri(String redirect_uri)
	{
		this.redirect_uri = redirect_uri;
	}
}
