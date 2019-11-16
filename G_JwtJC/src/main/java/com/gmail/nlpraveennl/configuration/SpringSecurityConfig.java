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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.gmail.nlpraveennl")
public class SpringSecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
    @Configuration
    @Order(1)
    public static class RestApiSecurityConfig extends WebSecurityConfigurerAdapter
    {
    	@Autowired
    	private JwtAuthenticationTokenFilter jwtauthFilter;
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception
    	{
            http
            	.csrf().disable()
	            .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
            	.antMatchers("/api/**").hasAnyRole("APIUSER")
            .and()
            	.addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class);
            
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }
    
    @Configuration
    @Order(2)
    public static class LoginFormSecurityConfig extends WebSecurityConfigurerAdapter
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
            	.csrf().disable()
            	.antMatcher("/**").authorizeRequests()
    		    .antMatchers("/resources/**").permitAll()
    		    .antMatchers("/**").hasRole("ADMIN")
    	    .and().formLogin();
            
            http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
        }
    }
}
