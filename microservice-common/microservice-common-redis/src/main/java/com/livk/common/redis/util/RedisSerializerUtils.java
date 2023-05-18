package com.livk.common.redis.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <p>
 * RedisSerializerUtils
 * </p>
 *
 * @author livk
 */
@UtilityClass
public class RedisSerializerUtils {

    public <T> RedisSerializer<T> getJacksonSerializer(ObjectMapper mapper, Class<T> targetClass) {
        var serializer = new Jackson2JsonRedisSerializer<>(mapper, targetClass);
        mapper.registerModule(new JavaTimeModule());
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        return serializer;
    }

    public <T> RedisSerializer<T> getJacksonSerializer(Class<T> targetClass) {
        return getJacksonSerializer(JsonMapper.builder().build(), targetClass);
    }
}
