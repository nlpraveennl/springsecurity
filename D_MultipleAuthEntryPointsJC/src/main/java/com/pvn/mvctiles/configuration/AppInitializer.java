package com.pvn.mvctiles.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

	@Override
	protected Class<?>[] getRootConfigClasses()
	{
		return new Class[] { RootConfiguration.class, SpringSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses()
	{
		return new Class[] { ServletConfiguration.class };
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
	
	@Override
    protected DispatcherServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(servletAppContext);
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }

}