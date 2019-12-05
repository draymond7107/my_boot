package com.draymond.my_boot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @Auther: ZhangSuchao
 * @Date: 2019/12/5 18:34
 */
@Log4j2
@Configuration
public class RedisConfig {

    private Duration timeToLive = Duration.ZERO;
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(this.timeToLive).serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer())).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer())).disableCachingNullValues();
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(config).transactionAware().build();
        log.debug("自定义RedisCacheManager加载完成");
        return redisCacheManager;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());        // key采用String的序列化方式
        redisTemplate.setHashKeySerializer(keySerializer());    //hash的key也采用String的序列化方式
        redisTemplate.setValueSerializer(valueSerializer());    //value序列化方式采用jackson
        redisTemplate.setHashValueSerializer(valueSerializer());//hash的value序列化方式采用jackson
        log.debug("自定义RedisTemplate加载完成");
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

}