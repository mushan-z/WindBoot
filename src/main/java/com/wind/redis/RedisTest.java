package com.wind.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhoubin
 * @time 2021/1/3 下午 5:57
 */
public class RedisTest {
    public static void main(String[] args) {
        //采用Bean配置redis连接工厂时使用
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
//        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
//        redisTemplate.opsForValue().set("aaa","aaa");
//        String value = (String)redisTemplate.opsForValue().get("aaa");
//        System.out.println("String: "+value);

        List<String> list = new ArrayList<>();
        list.forEach(x-> System.out.println(x));

    }
}
