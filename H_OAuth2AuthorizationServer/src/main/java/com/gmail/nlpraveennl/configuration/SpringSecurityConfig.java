package com.gmail.nlpraveennl.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
@ComponentScan(basePackages = "com.gmail.nlpraveennl")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin@123#")).roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.requestMatchers()
			.antMatchers("/resources/**", "/login**", "/oauth/**", "/")
			.and()
			.authorizeRequests()
	        .antMatchers("/resources/**", "/login**", "/oauth/authorize").permitAll()
	        .and()
	        .authorizeRequests()
	        .antMatchers("/","/**").hasRole("ADMIN")
	        .and()
	        .formLogin();
			 
		http
	    	.csrf().disable();
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
