package com.pvn.mvctiles.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:databaseconnection.properties" })
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
    private Environment environment;
	
	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password("$2a$10$qJTqy02X2rxhDsaQVjmGvuH5An4zaeGl38s9Ro/XqgcNvm0N464fi")
				.roles("ADMIN");
	}
	
	@Autowired
	public void configureJdbcAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username, password, enabled from userdetails where userName=?")
				.authoritiesByUsernameQuery(
						"select ud.username as username, rm.name as role from userdetails ud INNER JOIN rolemaster rm ON rm.id = ud.roleId  where username = ?");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/", "/login", "/api/**").permitAll()
				.antMatchers("/app/admin/*").hasRole("ADMIN").antMatchers("/app/user/*")
				.hasAnyRole("ADMIN", "USER")
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().httpBasic()
			.and().logout()
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())
				.invalidateHttpSession(true)
			.and().csrf().disable();

		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public DataSource dataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("driver"));
		dataSource.setUrl(environment.getRequiredProperty("url"));
		dataSource.setUsername(environment.getRequiredProperty("db.username"));
		dataSource.setPassword(environment.getRequiredProperty("password"));
		dataSource.setInitialSize(environment.getRequiredProperty("initialSize", Integer.class));
		dataSource.setMaxActive(environment.getRequiredProperty("maxActive", Integer.class));
		
		return dataSource;
	}
}
