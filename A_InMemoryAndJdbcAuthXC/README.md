# Fall Back authentication.

Here i have configured both in memory and jdbc authentication with default UserDetailService. In this example, during authentication if in memory authentication failed then jdbc authentication will be performed, this is often referred as Fall Back authentication.

In your ```spring-security.xml```

```xml
<authentication-manager>
    <authentication-provider>
        <user-service>
            <user name="admin" password="{bcrypt}$2a$10$qJTqy02X2rxhDsaQVjmGvuH5An4zaeGl38s9Ro/XqgcNvm0N464fi" authorities="ROLE_ADMIN" />
        </user-service>
    </authentication-provider>
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
```
### Some points for my referance
Configuring `PropertyPlaceholderConfigurer`
```xml
<beans:bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <beans:property name="locations" value="classpath:databaseconnection.properties" />
</beans:bean>
```
