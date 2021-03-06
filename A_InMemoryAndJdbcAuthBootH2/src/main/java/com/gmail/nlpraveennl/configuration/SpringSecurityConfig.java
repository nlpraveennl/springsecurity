package com.gmail.nlpraveennl.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:databaseconnection.properties" })
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PasswordEncoder passwordEncoder; 
	
	@Autowired
	public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
				.withUser("inmemory")
				.password(passwordEncoder.encode("inmemory@123#"))
				.roles("ADMIN");
	}
	
	@Autowired
	public void configureJdbcAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder)
				.usersByUsernameQuery("select username, password, enabled from userdetails where userName=?")
				.authoritiesByUsernameQuery(
						"select ud.username as username, rm.name as role from userdetails ud INNER JOIN rolemaster rm ON rm.id = ud.roleId  where username = ?");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http.csrf().disable();
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/", "/login", "/api/**").permitAll()
				.antMatchers("/app/admin/*").hasRole("ADMIN")
				.antMatchers("/app/user/*").hasAnyRole("ADMIN", "USER")
				.antMatchers("/h2-console/**").permitAll()
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().formLogin()
				.loginPage("/login").usernameParameter("userName")
				.passwordParameter("password")
				.defaultSuccessUrl("/app/user/dashboard")
				.failureUrl("/login?error=true")
			.and().logout()
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())
				.invalidateHttpSession(true);

		http.headers().frameOptions().disable();
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
	}
}
