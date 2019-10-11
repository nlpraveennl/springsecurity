package com.gmail.nlpraveennl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
@Order(2)
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter 
{
	@Override
	public void configure(HttpSecurity http) throws Exception 
	{
		http.anonymous().disable()
        	.authorizeRequests()
        	.antMatchers("/api/**").hasRole("ADMIN")
        	.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}
	
	@Primary
	@Bean
	public RemoteTokenServices tokenService() {
	    RemoteTokenServices tokenService = new RemoteTokenServices();
	    tokenService.setCheckTokenEndpointUrl(
	      "http://localhost:8080/spring-security-oauth2-all-in-one/oauth/check_token");
	    tokenService.setClientId("clientapp");
	    tokenService.setClientSecret("123456");
	    return tokenService;
	}
}
