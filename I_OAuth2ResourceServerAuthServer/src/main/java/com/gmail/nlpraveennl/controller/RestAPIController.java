package com.gmail.nlpraveennl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmail.nlpraveennl.vo.UserProfile;

@RestController
public class RestAPIController
{
	@RequestMapping("/api/hi")
	public String hi()
	{
		return "hi";
	}
	
	@RequestMapping("/api/hello")
	public String hello()
	{
		return "hello";
	}
	
	@RequestMapping("/api/users/me")
	public ResponseEntity<UserProfile> profile() 
	{
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = user.getUsername() + "@gmail.com";

		UserProfile profile = new UserProfile();
		profile.setName(user.getUsername());
		profile.setEmail(email);

		return ResponseEntity.ok(profile);
	}
}
