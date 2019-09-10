This project is configured with basic authentication.

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

