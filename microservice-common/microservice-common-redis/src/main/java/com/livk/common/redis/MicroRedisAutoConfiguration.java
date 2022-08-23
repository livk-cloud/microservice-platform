package com.livk.common.redis;

import com.livk.common.redis.support.MicroReactiveRedisTemplate;
import com.livk.common.redis.support.MicroRedisTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * <p>
 * MicroRedisAutoConfiguration
 * </p>
 *
 * @author livk
 * @date 2022/8/23
 */
@AutoConfiguration(before = RedisAutoConfiguration.class)
public class MicroRedisAutoConfiguration {

    @Bean
    public MicroRedisTemplate microRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new MicroRedisTemplate(redisConnectionFactory);
    }

    @Bean
    public MicroReactiveRedisTemplate microReactiveRedisTemplate(ReactiveRedisConnectionFactory redisConnectionFactory) {
        return new MicroReactiveRedisTemplate(redisConnectionFactory);
    }
}
