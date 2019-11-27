It has spring mvc Interceptor implementation. Where interceptor has been defined and added to Interceptor registry.

- preHandle, postHandle, afterCompletion
- `registry.addInterceptor(logInterceptor).addPathPatterns()`
 


Implement UserDetailService and Override loadUserByUsername method

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
@ComponentScan(basePackages = "com.pvn.mvctiles")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	UserDaoImpl userDaoImpl;

	@Autowired
	public void configureUserDetailsService(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDaoImpl);
	}
 
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
        http
        .authorizeRequests()
	        .antMatchers("/resources/**", "/", "/login")
	        	.permitAll()
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
        .and().csrf()
            	.disable();
        
        http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
    }
     
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }
}
