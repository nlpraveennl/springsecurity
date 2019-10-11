package com.gmail.nlpraveennl.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter 
{
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()")
			.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient("clientapp")
			.secret(new BCryptPasswordEncoder().encode("123456"))
			.authorizedGrantTypes("password", "authorization_code", "refresh_token")
			.authorities("READ_ONLY_CLIENT")
			.scopes("read_profile_info")
			.resourceIds("oauth2-resource")
			.redirectUris("http://localhost:8080/spring-security-oauth2-all-in-one/receivecode")
			.accessTokenValiditySeconds(1200)
			.refreshTokenValiditySeconds(6000);
	}
}
