Different authentications that can be configured in spring-security
# 1. Basic Authentication

Basic authentication is a simple authentication scheme built into the HTTP protocol. 
The client sends HTTP requests with the Authorization header that contains the word Basic word followed by a space 
and a base64-encoded string username:password. For example, to authorize as 
```
username :praveen
password :praveen@123# 
```
the client would send ```Basic base64(praveen:praveen@123#)``` 
```
Authorization: Basic cHJhdmVlbjpwcmF2ZWVuQDEyMyM=
```

If spring security is configured for basic authentication for access to resources other than ROLE_ANONYMOUS, 
it checks for access header <b>Authorization</b>

![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/basic-http-auth-header.png)

if Authorization header not present it will prompt for username and password as given below.

![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/basic-http.png)

In basic http authentication there is no concept of logout. Logout can be achieved manually by closing the browser.
If we need to automate logout by code, it can be done by forcing server to send 401(Un Authorized) status and overriting Authorization header with wrong credetials.

For more details, go through code and also https://stackoverflow.com/questions/233507/how-to-log-out-user-from-web-site-using-basic-authentication

# 2. In memory authentication
<b>Java configuration</b>
```java
@Autowired
public void configureInMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception
{
  auth.inMemoryAuthentication()
      .withUser("admin")
      .password("$2a$10$qJTqy02X2rxhDsaQVjmGvuH5An4zaeGl38s9Ro/XqgcNvm0N464fi")
      .roles("ADMIN");
}
```

```xml
<authentication-manager>
    <authentication-provider>
        <user-service>
            <user name="admin" password="{bcrypt}$2a$10$qJTqy02X2rxhDsaQVjmGvuH5An4zaeGl38s9Ro/XqgcNvm0N464fi" authorities="ROLE_ADMIN" />
        </user-service>
    </authentication-provider>
</authentication-manager>

<beans:bean id="encoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" />
```

# 3. JDBC Authentication
For implementing jdbcAuthentication you need to write two queries to your database tables<br>
**Query1:** ***usersByUsernameQuery*** (Sets the query to be used for finding a user by their username.)<br>
**Query2:** ***authoritiesByUsernameQuery*** (Sets the query to be used for finding a user's authorities by their username.)<br>

Either it may be java configuration or xml configuration, all you need to do is <br>
1. Create datasource<br>
2. Configure jdbc authentication in ```AuthenticationManagerBuilder``` by injecting datasource dependency and configuring usersByUsernameQuery and authoritiesByUsernameQuery.<br>
3. Configure HttpSecurity. Details and defaults given below <br>
----- Configure intercept-url pattern and authorization for those url's<br>
```
Default role of unauthenticated user = ROLE_ANONYMOUS
```
----- Configure form login to avoid default login screen and <b>default behavior given below</b>.<br>
```
login-page        = "/login" with HTTP get
usernameParameter = "username"
passwordParameter = "password"
failureUrl        = "/login?error"
loginProcessingUrl= "/login" with HTTP post
successUrl        = "/"
```
----- Configure logout to override default behavior.<br>
```
logoutUrl        = "/logout"
logoutSuccessUrl = "/login?logout"
```

----- Configure session management to override default behavior<br>
```
expiredUrl         = "/login?expired"
invalidate-session = true //you can set false and use delete-cookies="JSESSIONID"
maximumSessions    = The default is to allow any number of sessions for a users.

```

###Javaconfig way
If this is not sufficient download from my github repository. [Working copy of Spring security  with Java configuration](https://github.com/nlpraveennl/springsecurity-autologout/tree/master/javaconfig)

```java
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{

	public DataSource dataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvc");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setInitialSize(2);
		dataSource.setMaxActive(5);
		
		return dataSource;
	}

	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.jdbcAuthentication().dataSource(dataSource()).passwordEncoder(passwordEncoder())
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
	        .antMatchers("/config/*", "/app/admin/*")
	        .hasRole("ADMIN")
	        .antMatchers("/app/user/*")
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
```

### XML configuration way
If this is not sufficient download from my github repository. [Working copy of Spring security  with XML configuration](https://github.com/nlpraveennl/springsecurity-autologout/tree/master/xmlconfig)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns="http://www.springframework.org/schema/security"  
    xmlns:beans="http://www.springframework.org/schema/beans"  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
    xsi:schemaLocation="http://www.springframework.org/schema/beans  
    http://www.springframework.org/schema/beans/spring-beans.xsd  
    http://www.springframework.org/schema/security  
    http://www.springframework.org/schema/security/spring-security.xsd">  

	<http auto-config="true" use-expressions="true" create-session="ifRequired">
		<csrf disabled="true"/>
		
		<intercept-url pattern="/resources/**" access="permitAll" />
		<intercept-url pattern="/" access="permitAll" />
		<intercept-url pattern="/login" access="permitAll" />
		<intercept-url pattern="/api/**" access="permitAll" />
		
		<intercept-url pattern="/config/*" access="hasRole('ROLE_ADMIN')" />
		<intercept-url pattern="/app/admin/*" access="hasRole('ROLE_ADMIN')" />
		
		<intercept-url pattern="/app/user/*" access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')" />
		
		<access-denied-handler error-page="/403" />
		
		<form-login 
		    login-page="/login" 
		    default-target-url="/app/user/dashboard" 
			authentication-failure-url="/login?error=true" 
			username-parameter="userName"
			password-parameter="password" />
		
		<logout invalidate-session="false" success-handler-ref="customLogoutSuccessHandler"/>
			
		<session-management invalid-session-url="/login?expired=true">
			<concurrency-control max-sessions="1" />
		</session-management>
		
	</http>
	

	<authentication-manager>
	  <authentication-provider>
	  	<password-encoder ref="encoder" /> 
		<jdbc-user-service data-source-ref="dataSource"
		  users-by-username-query=
		    "select username, password, enabled from userdetails where userName=?"
		  authorities-by-username-query=
		    "select ud.username as username, rm.name as role from userdetails ud INNER JOIN rolemaster rm ON rm.id = ud.roleId  where username = ?" />
	  </authentication-provider>
	</authentication-manager>
	
	<beans:bean id="encoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" />
	<beans:bean id="customLogoutSuccessHandler" class="com.pvn.mvctiles.configuration.CustomLogoutSuccessHandler" />

	<beans:bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
		 <beans:property name="driverClassName" value="com.mysql.jdbc.Driver" />
		 <beans:property name="url" value="jdbc:mysql://localhost:3306/springmvc" />
		 <beans:property name="username" value="root"/>
		 <beans:property name="password" value="root"/>
		 <beans:property name="initialSize" value="2" />
		 <beans:property name="maxActive" value="5" />
	</beans:bean>		
</beans:beans>
```

# 4. Implement UserDetailsService in your Dao and inject UserDetailService in Authentication manager buider

Here you need to override loadUserByUsername method
1. load user **password** from database by username passed as arguement in the method.
2. Load **authorities** for user from database and construct a list of GrantedAuthority
3. Create User by passing password and authorities fetched in step 1 and step 2
4. Return UserDetail object so that authentication and authorization will be taken care by spring container itself.

```java
@Component
public class UserDaoImpl implements UserDao, UserDetailsService
{

	Logger			OUT	= LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	SessionFactory	sessionFactory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		try (Session session = sessionFactory.openSession();)
		{
			
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<DbUserDetails> userCriteria = criteriaBuilder.createQuery(DbUserDetails.class);
			Root<DbUserDetails> userRoot = userCriteria.from(DbUserDetails.class);
			userCriteria.select(userRoot).where(criteriaBuilder.equal(userRoot.get("userName"), username));
			
			Query<DbUserDetails> userQuery =session.createQuery(userCriteria);
			DbUserDetails dbUser = userQuery.getSingleResult();
			
			CriteriaQuery<RoleMaster> roleCriteria = criteriaBuilder.createQuery(RoleMaster.class);
			Root<RoleMaster> roleRoot = roleCriteria.from(RoleMaster.class);
			roleCriteria.select(roleRoot).where(criteriaBuilder.equal(roleRoot.get("id"), dbUser.getRoleId()));
			
			Query<RoleMaster> roleQuery =session.createQuery(roleCriteria);
			RoleMaster role = roleQuery.getSingleResult();
			
			List<GrantedAuthority> authList = new ArrayList<>();
			authList.add(new SimpleGrantedAuthority(role.getName()));
			
			return new User(username, dbUser.getPassword(),true, true, true, true, authList);
		}
		catch (Exception e)
		{
			OUT.error("Exception - {}", e);
			throw new UsernameNotFoundException("Exception caught", e);
		}
	}
}
```

```java
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{	
	@Autowired
	UserDaoImpl userDaoImpl;
	
	@Autowired
	public void configureUserDetailsService(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDaoImpl);
	}
    ...
}
```

# 5. By registering custom Authentication Provider.
In previous step(implementing UserDetailsService) loadUserByUsername method was passed only with username arguement (no way of getting password), but while authenticating with third party system, it will not provide you the stored credentials but instead it will ask you to provide credentials. Hence you need both username and password. You can get it by implementing AuthenticationProvider as given below. Once authentication is success against third party system you just need to set and return a token(UsernamePasswordAuthenticationToken) to the spring security.


```java
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
  
    @Autowired
    private CustomAuthenticationProvider authProvider;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
    
    ...
}
```

```java
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider 
{
 
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
  
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        //Do your authentication
        // use the credentials
        // and authenticate against the third-party system
        if (authenticatedSuccessfully) 
        {
            return new UsernamePasswordAuthenticationToken(
              name, password, new ArrayList<>());
        } 
        else 
        {
            return null;
        }
    }
 
    @Override
    public boolean supports(Class<?> authentication) 
    {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
```
