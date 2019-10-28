package com.pvn.mvctiles.configuration;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSecurity
@ComponentScan(value = "com.pvn.mvctiles.configuration")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	ApplicationContext context;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	RequestMatcher csrfProtectedMatchers;
	
	@Autowired
	MyAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
				.withUser("admin")
				.password(passwordEncoder.encode("admin@123#"))
				.roles("ADMIN");
	}
	

	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/", "/login").permitAll()
				.antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
				.antMatchers("/app/admin/*")
					.hasRole("ADMIN")
				.antMatchers("/app/user/*")
					.hasAnyRole("ADMIN", "USER")
			.and().formLogin()
				.loginPage("/login").usernameParameter("userName")
				.passwordParameter("password")
				.successHandler(authenticationSuccessHandler)
//				.defaultSuccessUrl("/app/user/dashboard")
				.failureUrl("/login?error=true")
			.and().logout()
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())
				.invalidateHttpSession(true)
			.and().csrf().requireCsrfProtectionMatcher(csrfProtectedMatchers);
	
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
	}
	
	@Bean
	public RequestMatcher getCsrfProtectedMatchers()
	{
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		AntPathMatcher antPathMatcher = new AntPathMatcher();
		
		List<String> protectedUrlPatterns = Arrays.asList("/api/**");
		
		return new RequestMatcher()
		{
			@Override
			public boolean matches(HttpServletRequest request)
			{
				String uri = urlPathHelper.getPathWithinApplication(request);
				for (String pattern : protectedUrlPatterns)
				{
					if (antPathMatcher.match(pattern, uri))
					{
						return true;
					}
				}
				return false;
			}
		};
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
