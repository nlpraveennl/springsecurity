package com.gmail.nlpraveennl.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@Order(4)
@ComponentScan("com.gmail.nlpraveennl.configuration")
public class AclConfiguration
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PermissionEvaluator permissionEvaluator; 
	
	@Bean
	public MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler()
	{
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//		AclPermissionEvaluator permissionEvaluator = new AclPermissionEvaluator(aclService());
		expressionHandler.setPermissionEvaluator(permissionEvaluator);
		return expressionHandler;
	}
	
	@Bean 
	public JdbcMutableAclService aclService() 
	{ 
	    return new JdbcMutableAclService(
	      dataSource, lookupStrategy(), aclCache()); 
	}
	
	@Bean
	public AclAuthorizationStrategy aclAuthorizationStrategy() 
	{
	    return new AclAuthorizationStrategyImpl(
	      new SimpleGrantedAuthority("ROLE_ADMIN"));
	}
	 
	@Bean
	public PermissionGrantingStrategy permissionGrantingStrategy() 
	{
	    return new DefaultPermissionGrantingStrategy(
	      new ConsoleAuditLogger());
	}
	 
	@Bean
	public EhCacheBasedAclCache aclCache() 
	{
	    return new EhCacheBasedAclCache(
	      aclEhCacheFactoryBean().getObject(), 
	      permissionGrantingStrategy(), 
	      aclAuthorizationStrategy()
	    );
	}
	 
	@Bean
	public EhCacheFactoryBean aclEhCacheFactoryBean() 
	{
	    EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
	    ehCacheFactoryBean.setCacheManager(aclCacheManager().getObject());
	    ehCacheFactoryBean.setCacheName("aclCache");
	    
	    return ehCacheFactoryBean;
	}
	 
	@Bean
	public EhCacheManagerFactoryBean aclCacheManager() 
	{
		EhCacheManagerFactoryBean ehcm = new EhCacheManagerFactoryBean();
		ehcm.setShared(true);
	    return ehcm;
	}
	 
	@Bean
	public LookupStrategy lookupStrategy() 
	{ 
	    return new BasicLookupStrategy(
	      dataSource, 
	      aclCache(), 
	      aclAuthorizationStrategy(), 
	      new ConsoleAuditLogger()
	    ); 
	}
}
