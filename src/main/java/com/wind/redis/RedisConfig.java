package com.wind.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * @author zhoubin
 * @time 2021/1/3 下午 5:30
 */
@Configuration
public class RedisConfig {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    //采用配置文件配置和Bean方式任选其一
//    @Bean(name = "RedisConnectionFactory")
//    public RedisConnectionFactory initRedisFactory(){
//        if(redisConnectionFactory!=null){
//            return this.redisConnectionFactory;
//        }
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(50);
//        jedisPoolConfig.setMaxWaitMillis(5000);
//        jedisPoolConfig.setMaxIdle(20);
//        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(jedisPoolConfig);
//        RedisStandaloneConfiguration singleConfig = connectionFactory.getStandaloneConfiguration();
//        singleConfig.setHostName("127.0.0.1");
//        singleConfig.setPort(6379);
//        redisConnectionFactory = connectionFactory;
//        return redisConnectionFactory;
//    }

    @Bean(name = "redisTemplate")
    public RedisTemplate getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer stringSerializer = redisTemplate.getStringSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }

}
