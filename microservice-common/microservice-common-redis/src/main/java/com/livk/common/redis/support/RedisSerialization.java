package com.livk.common.redis.support;

import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Nonnull;

/**
 * <p>
 * RedisSerialization
 * </p>
 *
 * @author livk
 */
public interface RedisSerialization<V> extends RedisSerializationContext<String, V> {

    static <T> Jackson2RedisSerializationContext<T> json(Class<T> targetClass) {
        return new Jackson2RedisSerializationContext<>(targetClass);
    }

    static Jackson2RedisSerializationContext<Object> json() {
        return json(Object.class);
    }

    static JdkRedisSerializationContext java() {
        return new JdkRedisSerializationContext();
    }

    @Nonnull
    @Override
    default SerializationPair<String> getKeySerializationPair() {
        return SerializationPair.fromSerializer(RedisSerializer.string());
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    @Override
    default <HK> SerializationPair<HK> getHashKeySerializationPair() {
        return (SerializationPair<HK>) SerializationPair.fromSerializer(RedisSerializer.string());
    }

    @Nonnull
    @Override
    default SerializationPair<String> getStringSerializationPair() {
        return SerializationPair.fromSerializer(RedisSerializer.string());
    }

}

