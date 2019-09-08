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
it checks for access header <b>Authorization</b>, if not present it will prompt for username and password as given below.
