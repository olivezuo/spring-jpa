package com.jin.data.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ConfigurationProperties(prefix = "com.jin.datasource.master")
public class MasterDBConfig extends DBConfig {

	@Bean
	public DataSource masterDataSource() {
		final DriverManagerDataSource masterDataSource = new DriverManagerDataSource();
		masterDataSource.setDriverClassName(driverClassName);
		masterDataSource.setUrl(url);
		masterDataSource.setUsername(username);
		masterDataSource.setPassword(password);

		return masterDataSource;
	}

}
