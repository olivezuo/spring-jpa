package com.jin.data.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@EnableCaching
public class RedisCacheConfig extends CacheConfig {

	@Autowired
	RedisProperties redisProperties;
	
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		//JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory(new RedisClusterConfiguration(redisProperties.getCluster().getNodes()));

		JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
		redisConnectionFactory.setHostName(redisProperties.getHost());
		redisConnectionFactory.setPort(redisProperties.getPort());
		return redisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}

	@Override
	public CacheManager cacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
		cacheManager.setUsePrefix(true);

		// Number of seconds before expiration. Defaults to unlimited (0)
		// cacheManager.setDefaultExpiration(0);
		return cacheManager;
	}

}
