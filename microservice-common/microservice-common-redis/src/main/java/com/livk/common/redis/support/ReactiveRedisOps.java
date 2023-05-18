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
 */
public class ReactiveRedisOps extends ReactiveRedisTemplate<String, Object> {

    public ReactiveRedisOps(ReactiveRedisConnectionFactory connectionFactory) {
        this(connectionFactory, RedisSerialization.json());
    }

    public ReactiveRedisOps(ReactiveRedisConnectionFactory connectionFactory,
                            RedisSerializationContext<String, Object> serializationContext) {
        super(connectionFactory, serializationContext);
    }

    public ReactiveRedisOps(ReactiveRedisConnectionFactory connectionFactory,
                            RedisSerializationContext<String, Object> serializationContext,
                            boolean exposeConnection) {
        super(connectionFactory, serializationContext, exposeConnection);
    }

}

