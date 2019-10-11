package com.gmail.nlpraveennl.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInitializer
{
	@Bean
	public FilterRegistrationBean<AccessTokenAlterFilter> sessionTimeoutFilter()
	{
		FilterRegistrationBean<AccessTokenAlterFilter> registrationBean = new FilterRegistrationBean<>();
		AccessTokenAlterFilter filter = new AccessTokenAlterFilter();

		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/oauth/token");
		registrationBean.setOrder(1); // set precedence
		return registrationBean;
	}
}