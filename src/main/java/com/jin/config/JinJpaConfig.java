package com.jin.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import com.jin.data.jpa.JPAConfig;

@Configuration
public class JinJpaConfig extends JPAConfig {

	@Override
	@PostConstruct
	public void init() {
		this.entityPackages = new String[]{"com.jin.domain"};

	}

}
