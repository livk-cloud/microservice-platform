package com.livk.common.redis.support;

import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Nonnull;

/**
 * <p>
 * JdkRedisSerializationContext
 * </p>
 *
 * @author livk
 */
public class JdkRedisSerializationContext implements RedisSerialization<Object> {

    @Nonnull
    @Override
    public SerializationPair<Object> getValueSerializationPair() {
        return SerializationPair.fromSerializer(RedisSerializer.java());
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    public <HV> SerializationPair<HV> getHashValueSerializationPair() {
        return (SerializationPair<HV>) SerializationPair.fromSerializer(RedisSerializer.java());
    }

}
