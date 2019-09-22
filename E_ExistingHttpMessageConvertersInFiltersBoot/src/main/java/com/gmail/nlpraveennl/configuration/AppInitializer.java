package com.gmail.nlpraveennl.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitializer
{

	@Bean
	public FilterRegistrationBean<SessionTimeoutCheckFilter> sessionTimeoutFilter()
	{
		FilterRegistrationBean<SessionTimeoutCheckFilter> registrationBean = new FilterRegistrationBean<>();
		SessionTimeoutCheckFilter filter = new SessionTimeoutCheckFilter();

		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/api/sessionValid");
		registrationBean.setOrder(1); // set precedence
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<SessionLastAccessTimeUpdateFilter> sessionLastTimeAccessTimeUpdateFilter()
	{
		FilterRegistrationBean<SessionLastAccessTimeUpdateFilter> registrationBean = new FilterRegistrationBean<>();

		SessionLastAccessTimeUpdateFilter filter2 = new SessionLastAccessTimeUpdateFilter();
		registrationBean.setFilter(filter2);
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(2); // set precedence

		return registrationBean;
	}

}