package com.pvn.mvctiles.exceptionhandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler
{
	@ExceptionHandler(NoHandlerFoundException.class)
	public String handleNotFoundError(Exception ex)
	{
		return "404";
	}
	
	@ExceptionHandler(RuntimeException.class)
    public ModelAndView handle(RuntimeException ex)
	{
        ModelAndView model = new ModelAndView("runtime-exception");
        model.addObject("exception", ex);
        return model;
    }
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception ex)
	{
        ModelAndView model = new ModelAndView("exception");
        model.addObject("exception", ex);
        return model;
    }
}
