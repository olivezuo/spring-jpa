package com.jin.data.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "com.jin.datasource.slave")
public class SlaveDBConfig extends HikariConfig{

	@Bean
	public DataSource slaveDataSource() {
		final HikariDataSource slaveDataSource = new HikariDataSource(this);

		return slaveDataSource;
	}

}
