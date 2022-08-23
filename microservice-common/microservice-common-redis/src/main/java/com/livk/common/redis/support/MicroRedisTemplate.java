package com.livk.common.redis.support;

import com.livk.common.redis.util.RedisSerializerUtils;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * <p>
 * LivkRedisTemplate
 * </p>
 *
 * @author livk
 * @date 2022/8/15
 */
public class MicroRedisTemplate extends RedisTemplate<String, Object> {

    private MicroRedisTemplate() {
        this.setKeySerializer(RedisSerializer.string());
        this.setHashKeySerializer(RedisSerializer.string());
        this.setValueSerializer(RedisSerializerUtils.getJacksonSerializer(Object.class));
        this.setHashValueSerializer(RedisSerializerUtils.getJacksonSerializer(Object.class));
    }

    public MicroRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        this();
        this.setConnectionFactory(redisConnectionFactory);
        this.afterPropertiesSet();
    }

}
