package com.livk.common.redis.support;

import com.livk.common.redis.util.RedisSerializerUtils;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Nonnull;

/**
 * <p>
 * Jackson2RedisSerializationContext
 * </p>
 *
 * @author livk
 */
public class Jackson2RedisSerializationContext<T> implements RedisSerialization<T> {

    private final RedisSerializer<T> serializer;

    public Jackson2RedisSerializationContext(Class<T> targetClass) {
        this.serializer = RedisSerializerUtils.getJacksonSerializer(targetClass);
    }

    @Nonnull
    @Override
    public SerializationPair<T> getValueSerializationPair() {
        return SerializationPair.fromSerializer(serializer);
    }

    @SuppressWarnings("unchecked")
    @Nonnull
    @Override
    public <HV> SerializationPair<HV> getHashValueSerializationPair() {
        return (SerializationPair<HV>) SerializationPair.fromSerializer(serializer);
    }

}
