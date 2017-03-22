package com.jin.data.jpa;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.jin.data.datasource.DynamicDataSource;

@EnableConfigurationProperties(JpaProperties.class)
public abstract class JPAConfig {

	@Autowired
	JpaProperties jpaProperties;
	
	@Autowired
	DynamicDataSource dynamicDataSource;

	protected String[] entityPackages = new String[]{};
	
	public void setEntityPackages(String[] entityPackages ){
		this.entityPackages = entityPackages;
	}

	public abstract void init();

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dynamicDataSource);
		em.setPackagesToScan(entityPackages);

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		final HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", jpaProperties.getHibernate().getDdlAuto());
		properties.put("hibernate.dialect", jpaProperties.getDatabasePlatform());
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

}
