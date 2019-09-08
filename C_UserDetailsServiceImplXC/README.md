#Custom User Details service

Implement UserDetailService and Override loadUserByUsername method.

And configure spring-security.xml with UserDetailService reference as Authentication provider.

```xml
<authentication-manager>
  <authentication-provider>
        <user-service>
            <user name="restapiuser" password="{bcrypt}$2a$10$GRoNCbeVoBYMcZH7QLX2O.wWxkMtB4qiBY8y.XzvRN/mvktS9GWc6" authorities="ROLE_APIUSER" />
        </user-service>
    </authentication-provider>
    <authentication-provider user-service-ref="userDaoImpl">
      <password-encoder ref="encoder"/>
    </authentication-provider>
</authentication-manager>
```
