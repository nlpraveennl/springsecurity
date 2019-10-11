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

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username, password, enabled from userdetails where userName=?")
				.authoritiesByUsernameQuery(
						"SELECT ud.username AS username, rm.name AS role FROM user_role_mapping map " + 
						"INNER JOIN userdetails ud ON map.userId = ud.id " + 
						"INNER JOIN rolemaster rm ON  map.roleId = rm.id  where userName = ?");
	}
 
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
        http
        .authorizeRequests()
	        .antMatchers("/resources/**", "/", "/login", "/api/**").permitAll()
	        .antMatchers("/config/**", "/app/admin/**")
	        	.hasRole("ADMIN")
	        .antMatchers("/app/user/**")
	        .hasAnyRole("ADMIN", "USER")
        .and().exceptionHandling()
        	.accessDeniedPage("/403")
        .and().formLogin()
            .loginPage("/login")
            .usernameParameter("userName").passwordParameter("password")
            .defaultSuccessUrl("/app/user/dashboard")
            .failureUrl("/login?error=true")
        .and().logout()
            .logoutSuccessHandler(new CustomLogoutSuccessHandler())
            .invalidateHttpSession(true)
        .and()
            .csrf()
            	.disable();
        
        http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
    
}
