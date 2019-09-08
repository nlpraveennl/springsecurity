package com.pvn.mvctiles.validators;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pvn.mvctiles.model.UserDetails;

@Component
public class UserValidator implements Validator
{
	@Autowired
	MessageSource messageSource;
	
	public boolean supports(Class<?> candidate)
	{
		return UserDetails.class.isAssignableFrom(candidate);
	}

	public void validate(Object obj, Errors errors)
	{
		String userMsg = messageSource.getMessage("app.login.required", new String[] { "Username" }, Locale.ENGLISH);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "app.login.required", new String[] { "Username" }, userMsg);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "app.login.required", new String[] { "Password" }, "Field is required.");
	}
}
