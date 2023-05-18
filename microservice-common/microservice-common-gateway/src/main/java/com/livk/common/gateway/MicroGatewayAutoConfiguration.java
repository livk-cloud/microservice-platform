package com.livk.common.gateway;

import com.livk.common.gateway.support.MicroRedisRouteDefinitionRepository;
import com.livk.common.gateway.support.RedisRouteHealthIndicator;
import com.livk.common.redis.MicroRedisAutoConfiguration;
import com.livk.common.redis.support.ReactiveRedisOps;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.route.RedisRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>
 * LivkDynamicAutoConfig
 * </p>
 *
 * @author livk
 */
@EnableConfigurationProperties(MicroRouteProperties.class)
@AutoConfiguration(before = {GatewayAutoConfiguration.class, MicroRedisAutoConfiguration.class})
public class MicroGatewayAutoConfiguration {


    @Bean
    @ConditionalOnProperty(prefix = MicroRouteProperties.PREFIX, name = "type", havingValue = "REDIS_HASH")
    public MicroRedisRouteDefinitionRepository redisRouteDefinitionWriter(ReactiveRedisOps reactiveRedisTemplate) {
        return new MicroRedisRouteDefinitionRepository(reactiveRedisTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = MicroRouteProperties.PREFIX, name = "type", havingValue = "REDIS_STR")
    public RouteDefinitionRepository redisRouteDefinitionRepository(ReactiveRedisConnectionFactory factory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<RouteDefinition> valueSerializer = new Jackson2JsonRedisSerializer<>(
                RouteDefinition.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, RouteDefinition> builder = RedisSerializationContext
                .newSerializationContext(keySerializer);
        RedisSerializationContext<String, RouteDefinition> context = builder.value(valueSerializer).build();

        ReactiveRedisTemplate<String, RouteDefinition> reactiveRedisTemplate = new ReactiveRedisTemplate<>(factory, context);
        return new RedisRouteDefinitionRepository(reactiveRedisTemplate);
    }

    @Bean
    @ConditionalOnBean(ReactiveRedisTemplate.class)
    public RedisRouteHealthIndicator redisRouteHealthIndicator(ReactiveRedisOps reactiveRedisOps) {
        return new RedisRouteHealthIndicator(reactiveRedisOps);
    }
}
