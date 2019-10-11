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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig
{
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
    @Configuration
    @Order(1)
    public static class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter
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
            http.csrf().disable()
            	.antMatcher("/api/**")
                .authorizeRequests()
            	.antMatchers("/api/**").hasRole("USER")
    	    .and().httpBasic();
            
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
    		auth.inMemoryAuthentication()
    				.withUser("admin")
    				.password(passwordEncoder.encode("admin@123#"))
    				.roles("ADMIN");
    	}
    	
    	@Bean
        public JwtAuthenticationTokenFilter authenticationTokenFilterBean() {
            return new JwtAuthenticationTokenFilter();
        }
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception
    	{
            http
            	.csrf().disable()
            	.antMatcher("/app/**").authorizeRequests()
    		        .antMatchers("/resources/**")
    		        	.permitAll()
    		    .antMatchers("/app/**").authenticated()
    	    .and().formLogin()
    	    	.defaultSuccessUrl("/app/user/dashboard")
            .and().exceptionHandling()
            	.accessDeniedPage("/403")
            	.authenticationEntryPoint(loginUrlauthenticationEntryPoint())
            .and().logout()
                .logoutSuccessUrl("/customlogin")
                .invalidateHttpSession(true);
            
            http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
            http.sessionManagement().maximumSessions(1).expiredUrl("/customlogin?expired=true");
        }
    	
    	@Bean
    	public AuthenticationEntryPoint loginUrlauthenticationEntryPoint()
    	{
    	    return new LoginUrlAuthenticationEntryPoint("/customlogin");
    	}
    }
}
