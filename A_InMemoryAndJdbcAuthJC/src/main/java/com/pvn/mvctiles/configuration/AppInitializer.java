package com.pvn.mvctiles.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[] { SpringSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[] { ApplicationConfiguration.class };
	}

	@Override
	protected String[] getServletMappings()
	{
		return new String[] { "/" };
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		super.onStartup(servletContext);

	    servletContext.addFilter("sessionTimeoutCheckFilter", new SessionTimeoutCheckFilter())
	    .addMappingForUrlPatterns(null, false, "/api/sessionValid");
	    
	    servletContext.addFilter("sessionLastAccessTimeUpdateFilter", new SessionLastAccessTimeUpdateFilter())
        .addMappingForUrlPatterns(null, true, "/*");
	    
	    servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
        .addMappingForUrlPatterns(null, true, "/*");
	    
		servletContext.addListener(new SessionListener());
	}

}