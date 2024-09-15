package com.Aaron.MFM.common.contanst;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

//    @Bean
//    public RedisTemplate<String, Integer> redisTemplate(RedisConnectionFactory factory){
//        RedisTemplate<String, Integer> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        // 设置key和value的序列化方式
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }

    @Bean
    public RedisConnectionFactory ChatCacheConnectionFactory()
    {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setDatabase(2);
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> ChatCacheRedisTemplate(RedisConnectionFactory chatCacheConnectionFactory)
    {
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(chatCacheConnectionFactory);
        configureRedisTemplate(template);
        return template;
    }

    // 配置RedisTemplate
    private void configureRedisTemplate(RedisTemplate<String, Object> template)
    {
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();// 初始化
    }
}
