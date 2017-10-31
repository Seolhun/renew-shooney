//package com.hun.blog.config.config;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.lang.reflect.Method;
//
//@Configuration
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport {
//
//	@Bean
//	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//		redisTemplate.setConnectionFactory(redisConnectionFactory);
//		return redisTemplate;
//	}
//
//	@Bean(name = "cacheManager")
//	public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
//		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//		// Number of seconds before expiration. Defaults to unlimited (0)
//		cacheManager.setDefaultExpiration(60 * 30);
//		return cacheManager;
//	}
//
//	@Bean
//	public KeyGenerator keyGenerator() {
//		return new KeyGenerator() {
//			@Override
//			public Object generate(Object object, Method method, Object... params) {
//				StringBuilder stringBuilder = new StringBuilder();
//				stringBuilder.append(object.getClass().getName());
//				stringBuilder.append(method.getName());
//				for (Object param : params) {
//					stringBuilder.append(param.toString());
//				}
//				return stringBuilder.toString();
//			}
//		};
//	}
//}