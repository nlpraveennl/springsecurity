# Fall Back authentication.

Here i have configured both in memory and jdbc authentication with default UserDetailService. In this example, during authentication if in memory authentication failed then jdbc authentication will be performed, this is often referred as Fall Back authentication.


```java
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
```

<b><i>Or</i></b>

```java
@Autowired
public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception
{
  auth.inMemoryAuthentication()
      .withUser("admin")
      .password("$2a$10$qJTqy02X2rxhDsaQVjmGvuH5An4zaeGl38s9Ro/XqgcNvm0N464fi")
      .roles("ADMIN");
      
  auth.jdbcAuthentication()
      .dataSource(dataSource)
      .passwordEncoder(passwordEncoder())
      .usersByUsernameQuery("select username, password, enabled from userdetails where userName=?")
      .authoritiesByUsernameQuery(
          "select ud.username as username, rm.name as role from userdetails ud INNER JOIN rolemaster rm ON rm.id = ud.roleId  where username = ?");
}
```
