package com.gmail.nlpraveennl.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable
{
	private static final long	serialVersionUID	= 8544329907338151549L;
	public static final long	JWT_TOKEN_VALIDITY	= 5 * 60 * 60;
	private String				secret				= "my-secret";

	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(String username)
	{
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, username);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject)
	{
		return "Bearer "+Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean validateToken(String token, String usernameFromToken)
	{
		final String username = getUsernameFromToken(token);
		return (username.equals(usernameFromToken) && !isTokenExpired(token));
	}
	
	public static void main(String[] args)
	{
		JwtTokenUtil tu = new JwtTokenUtil();
		String s1 = tu.generateToken("praveen");
		System.out.println(s1);
		String user = tu.getUsernameFromToken(s1);
		System.out.println(user);
	}
}
