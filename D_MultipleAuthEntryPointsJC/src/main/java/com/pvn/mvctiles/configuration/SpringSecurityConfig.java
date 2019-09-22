package com.pvn.mvctiles.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pvn.mvctiles.authentication.UserCustomAuthenticationProvider;
import com.pvn.mvctiles.authentication.AdminCustomAuthenticationProvider;
import com.pvn.mvctiles.authentication.CustomAuthenticationFailureHandler;
import com.pvn.mvctiles.authentication.CustomAuthenticationSuccessHandler;
import com.pvn.mvctiles.authentication.MyAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private AdminCustomAuthenticationProvider adminCustomAuthenticationProvider;
	
	@Autowired
	private UserCustomAuthenticationProvider userCustomAuthenticationProvider;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(adminCustomAuthenticationProvider);
		auth.authenticationProvider(userCustomAuthenticationProvider);
	}
	
	@Bean
	public MyAuthenticationFilter myAuthenticationFilter() throws Exception
	{
		MyAuthenticationFilter authenticationFilter = new MyAuthenticationFilter();

		authenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
		authenticationFilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
		authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		authenticationFilter.setAuthenticationManager(authenticationManagerBean());

		return authenticationFilter;
	}
 
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
        http
        .addFilterBefore(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        .csrf().disable()
        .authorizeRequests()
	        .antMatchers("/resources/**", "/", "/login")
	        	.permitAll()
	        .antMatchers("/config/*", "/app/admin/*")
	        	.hasRole("ADMIN")
	        .antMatchers("/app/user/*","/api/**")
	        	.hasAnyRole("ADMIN", "USER")
        .and().exceptionHandling()
        	.accessDeniedPage("/403")
        .and().logout()
            .logoutSuccessHandler(new CustomLogoutSuccessHandler())
            .invalidateHttpSession(true);
        
        http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
}
