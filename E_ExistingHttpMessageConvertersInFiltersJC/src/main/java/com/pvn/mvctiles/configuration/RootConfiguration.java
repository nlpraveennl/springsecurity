package com.pvn.mvctiles.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@PropertySource(value = { "classpath:databaseconnection.properties" })
@ComponentScan(basePackages = { "com.pvn.mvctiles.configuration", "org.springframework.web.servlet" })
public class RootConfiguration
{
	Logger OUT = LoggerFactory.getLogger(RootConfiguration.class);
	
	@Autowired
    private Environment environment;
	
	@Bean
	public DataSource dataSource()
	{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("driver"));
		dataSource.setUrl(environment.getRequiredProperty("url"));
		dataSource.setUsername(environment.getRequiredProperty("db.username"));
		dataSource.setPassword(environment.getRequiredProperty("password"));
		dataSource.setInitialSize(environment.getRequiredProperty("initialSize", Integer.class));
		dataSource.setMaxActive(environment.getRequiredProperty("maxActive", Integer.class));
		
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.pvn.mvctiles.model");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
}
