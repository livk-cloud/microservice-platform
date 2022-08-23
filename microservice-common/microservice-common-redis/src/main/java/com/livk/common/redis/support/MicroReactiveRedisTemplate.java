package com.livk.common.redis.support;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * <p>
 * LivkReactiveRedisTemplate
 * </p>
 *
 * @author livk
 * @date 2022/8/15
 */
public class MicroReactiveRedisTemplate extends ReactiveRedisTemplate<String, Object> {

    public MicroReactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory) {
        this(connectionFactory, RedisSerialization.json());
    }

    public MicroReactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory,
                                     RedisSerializationContext<String, Object> serializationContext) {
        super(connectionFactory, serializationContext);
    }

    public MicroReactiveRedisTemplate(ReactiveRedisConnectionFactory connectionFactory,
                                     RedisSerializationContext<String, Object> serializationContext,
                                     boolean exposeConnection) {
        super(connectionFactory, serializationContext, exposeConnection);
    }

}

