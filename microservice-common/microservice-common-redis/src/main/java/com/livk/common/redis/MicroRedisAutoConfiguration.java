package com.livk.common.redis;

import com.livk.auto.service.annotation.SpringAutoService;
import com.livk.common.redis.support.ReactiveRedisOps;
import com.livk.common.redis.support.RedisOps;
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
 */
@SpringAutoService
@AutoConfiguration(before = RedisAutoConfiguration.class)
public class MicroRedisAutoConfiguration {

    @Bean
    public RedisOps redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new RedisOps(redisConnectionFactory);
    }

    @Bean
    public ReactiveRedisOps reactiveRedisOps(ReactiveRedisConnectionFactory redisConnectionFactory) {
        return new ReactiveRedisOps(redisConnectionFactory);
    }
}
