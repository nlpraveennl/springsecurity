package com.gmail.nlpraveennl.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MyResponse extends ResponseEntity<MyResponse>
{

	public MyResponse(HttpStatus status)
	{
		super(status);
	}

}
