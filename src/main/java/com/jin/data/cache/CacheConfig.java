package com.jin.data.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(CacheProperties.class)
public abstract class CacheConfig extends CachingConfigurerSupport {
	private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
	
	@Autowired
	CacheProperties cacheProperties;
	
	public abstract CacheManager cacheManager();
	
	public CacheManager noOpCacheManager(){
		
		return new NoOpCacheManager();
		
	}
	
	@Bean
	public CacheResolver cacheResolver() {
		CacheResolver cacheResolver;
	
		logger.info("Current Cache Type is: " + cacheProperties.getType().name());
		if (cacheProperties.getType().name().equals("NONE")){
			cacheResolver = new SimpleCacheResolver(noOpCacheManager());
		} else {
			cacheResolver = new SimpleCacheResolver(cacheManager());
		}
		return cacheResolver;
	}
}