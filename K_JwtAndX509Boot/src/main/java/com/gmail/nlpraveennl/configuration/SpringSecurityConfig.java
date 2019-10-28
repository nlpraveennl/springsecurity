package com.gmail.nlpraveennl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String API_KEY_HEADER = "x-api-key";
    
    private String apiKey = "SomeKey1234567890";
    
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
    	APIKeyFilter filter = new APIKeyFilter(API_KEY_HEADER);
    	filter.setAuthenticationManager(authentication -> {
    		if(authentication.getPrincipal() == null)
    		{
    			throw new BadCredentialsException("Access Denied."); // required if you configure http
    		}
            String apiKey = (String) authentication.getPrincipal();
            if (authentication.getPrincipal() != null && this.apiKey.equals(apiKey)) 
            {
                authentication.setAuthenticated(true);
                return authentication;
            }
            else
            {
                throw new BadCredentialsException("Access Denied.");
            }
        });
        
    	http.antMatcher("/v1/**")
		        .csrf().disable()
		        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
		        .addFilter(filter)
		        .authorizeRequests()
		        .anyRequest()
		        .authenticated();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
