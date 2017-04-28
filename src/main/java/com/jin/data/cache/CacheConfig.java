package com.jin.data.cache;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
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

	public CacheManager noOpCacheManager() {

		return new NoOpCacheManager();

	}

	@Bean
	public CacheResolver cacheResolver() {
		CacheResolver cacheResolver;

		logger.info("Current Cache Type is: " + cacheProperties.getType().name());
		if (cacheProperties.getType().name().equals("NONE")) {
			cacheResolver = new SimpleCacheResolver(noOpCacheManager());
		} else {
			cacheResolver = new SimpleCacheResolver(cacheManager());
		}
		return cacheResolver;
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return new CacheErrorHandler() {

			@Override
			public void handleCacheClearError(RuntimeException ex, Cache cache) {
				logger.warn(
						"Exception occured when fetching an item from Redis.Time : {} , Cache : {}, Exception : ",
						LocalDateTime.now(), cache.getName(), ex);
			}

			@Override
			public void handleCacheEvictError(RuntimeException ex, Cache cache, Object key) {
				logger.warn(
						"Exception occured when fetching an item from Redis.Time : {} , Key : {} , Cache : {}, Exception : ",
						LocalDateTime.now(), key, cache.getName(), ex);
			}

			@Override
			public void handleCacheGetError(RuntimeException ex, Cache cache, Object key) {
				logger.warn(
						"Exception occured when fetching an item from Redis.Time : {} , Key : {} , Cache : {}, Exception : ",
						LocalDateTime.now(), key, cache.getName(), ex);
			}

			@Override
			public void handleCachePutError(RuntimeException ex, Cache cache, Object arg1, Object arg2) {
				logger.warn(
						"Exception occured when fetching an item from Redis.Time : {} , arg1 : {} , arg2 : {} , Cache : {}, Exception : ",
						LocalDateTime.now(), arg1, arg2, cache.getName(), ex);
			}
		};
	}

}