package com.jin.data.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ConfigurationProperties(prefix = "com.jin.datasource.slave")
public class SlaveDBConfig extends DBConfig{

	@Bean
	public DataSource slaveDataSource() {
		final DriverManagerDataSource slaveDataSource = new DriverManagerDataSource();
		slaveDataSource.setDriverClassName(driverClassName);
		slaveDataSource.setUrl(url);
		slaveDataSource.setUsername(username);
		slaveDataSource.setPassword(password);

		return slaveDataSource;
	}

}
