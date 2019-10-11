package com.pvn.mvctiles.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class SpringSecurityConfiguration
{
//    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
//    @Configuration
//    @Order(1)
    public static class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter
    {
    	@Autowired
    	private PasswordEncoder passwordEncoder;
    	
    	@Autowired
    	public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
    	{
    		auth.inMemoryAuthentication()
    				.withUser("superadmin")
    				.password(passwordEncoder.encode("superadmin@123#"))
    				.roles("SUPER_ADMIN");
    	}
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception
    	{
            http.csrf().disable()
            	.antMatcher("/api/**")
            		.authorizeRequests()
            	.antMatchers("/api/**").hasRole("SUPER_ADMIN")
    	    .and().httpBasic();
            
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }
    
//    @Configuration
//    @Order(2)
    public static class LoginFormSecurityConfig extends WebSecurityConfigurerAdapter
    {
    	@Autowired
    	private PasswordEncoder passwordEncoder;
    	
    	@Autowired
    	public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
    	{
    		auth.inMemoryAuthentication()
    				.withUser("user")
    				.password(passwordEncoder.encode("user@123#"))
    				.roles("USER");
    	}
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception
    	{
            http
            	.antMatcher("/**")
            		.authorizeRequests()
    		    .antMatchers("/resources/**").permitAll()
    		    .antMatchers("/**").authenticated()
    	    .and().formLogin()
    	    	.defaultSuccessUrl("/app/user/dashboard")
            .and().exceptionHandling()
            	.accessDeniedPage("/403")
            .and().logout()
                .invalidateHttpSession(true);
            
            http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
        }
    }
}
