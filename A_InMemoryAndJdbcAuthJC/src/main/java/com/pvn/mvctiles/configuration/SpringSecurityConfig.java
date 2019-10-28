package com.pvn.mvctiles.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:databaseconnection.properties" })
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	ApplicationContext context;
	
	@Autowired
    private Environment environment;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	@Qualifier("bcryptPasswordEncoder")
	PasswordEncoder bcrypt;
	
	@Autowired
	@Qualifier("md4PasswordEncoder")
	PasswordEncoder md4;
	

	@Autowired
	public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().passwordEncoder(md4)
				.withUser("admin")
				.password(md4.encode("admin@123#"))
				.roles("ADMIN");
	}
	
	@Autowired
	public void configureJdbcAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.passwordEncoder(bcrypt)
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
//				.antMatchers("/app/admin/app-config").permitAll()
				.antMatchers("/app/admin/**").hasRole("ADMIN")
				.antMatchers("/app/user/**").hasAnyRole("ADMIN", "USER")
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().formLogin()
				.loginPage("/login").usernameParameter("userName")
				.passwordParameter("password")
				.defaultSuccessUrl("/app/user/dashboard")
				.failureUrl("/login?error=true")
			.and().logout()
				.logoutSuccessHandler(new CustomLogoutSuccessHandler())
				.invalidateHttpSession(true)
			.and().csrf().disable();
	
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
	}
	
	@Bean
    public PasswordEncoder bcryptPasswordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public PasswordEncoder md4PasswordEncoder() 
    {
        return new Md4PasswordEncoder();
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
