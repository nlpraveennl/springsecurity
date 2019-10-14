Oauth2 is all about securing your API's. it has mainly 4 parts. Lets take exapmle of facebook<br>
1. Authorization Server [Facebook]<br>
2. Resource server [Facebook and resource will be your profile]<br>
3. Client [Stack overflow, Quora, Candy crush, Subway Surfer etc]<br>
4. Resource owner [You (If Authenticated)]<br>

Resource server may consists of both secured and unsecured API's. For accessing secured API's Client need access_token which is issued by Authorization server. access_token issued is a random string and is also stored in authorization server database along with the associated user, scope(read, read_profile_info, write).

Here Resource owner(You) giving consent to the Authorization server to grant a access_token of scope('read','read-profile',`post-to-my-timeline` etc) to the Client(`Quora`,`StackOverflow`,`Candy-Crush` etc)

Advantage of oauth2.0<br>
1. access_token received will have authentication and authorization both. So it will be helpful to provide specific scope to access_token.<br>
   (Say stack overflow accesses basic profile info, candy crush accesses more information including scope of posting on behalf of you)
2. life span of access_token, grant_type of refresh_token.<br>
   Access tokens have limited lifetimes. If client application needs access to Resource beyond the lifetime of a single access token, it can obtain a refresh token. A refresh token allows client application to obtain new access tokens.<br>
   grant types: (authorization code, implicit, password, client credntial, refresh token)<br>

Note: Oauth2 Auth server not only for applications like facebook and Google but also Your application can have oauth2 server set up, and you can provide your clients access_token (to integrate your API with their application) with different scope, lifespan based on subscription/license.

## Some of the screen shots related to oauth2 are

When secured resource is accessed without access_token
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/accessing_secured_resource_without_access_token.png)

When secured resource is accessed with valid access_token from browser GET request
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/access_approach1.png)

When secured resource is accessed with valid access_token from postman with `POST` request and `Content-Type=application/x-www-form-urlencoded`
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/access_approach2.png)

When secured resource is accessed with valid access_token from postman with header `Authorization=Bearer acccess_token`
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/access_approach3.png)

## Steps in getting access token
User logs in to application.
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/oauth_weblogin.png)

User requests authorzation code
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/oauth_postlogin.png)

Auth server expects a user consent as below
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/oauth_authorize.png)

User receives authorization code and User handover authorization code to client and client will request for access_token
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/redirectUri_recceivedCodeInQueryString.png)

Client receives access_token
![loginscreen](https://github.com/nlpraveennl/springsecurity/blob/master/z_screenshots/access_token_rcvd.png)
