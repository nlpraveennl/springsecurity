package com.gmail.nlpraveennl.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class ApplicationConfiguration
{
	@Value("${db.driver}")
	private String	DB_DRIVER;

	@Value("${db.password}")
	private String	DB_PASSWORD;

	@Value("${db.url}")
	private String	DB_URL;

	@Value("${db.username}")
	private String	DB_USERNAME;

	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}
}