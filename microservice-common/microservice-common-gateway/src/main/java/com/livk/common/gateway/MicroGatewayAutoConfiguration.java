package com.livk.common.gateway;

import com.livk.common.core.function.ThrowException;
import com.livk.common.gateway.exception.RouteCheckException;
import com.livk.common.gateway.support.MicroRedisRouteDefinitionRepository;
import com.livk.common.gateway.support.RedisRouteHealthIndicator;
import com.livk.common.redis.MicroRedisAutoConfiguration;
import com.livk.common.redis.support.MicroReactiveRedisTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.cloud.gateway.route.RedisRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

/**
 * <p>
 * LivkDynamicAutoConfig
 * </p>
 *
 * @author livk
 * @date 2021/12/28
 */
@EnableConfigurationProperties(MicroRouteProperties.class)
@AutoConfiguration(before = {GatewayAutoConfiguration.class, MicroRedisAutoConfiguration.class})
public class MicroGatewayAutoConfiguration {


    @Bean
    @ConditionalOnProperty(prefix = MicroRouteProperties.PREFIX, name = "type", havingValue = "REDIS_HASH")
    public MicroRedisRouteDefinitionRepository redisRouteDefinitionWriter(
            MicroReactiveRedisTemplate livkReactiveRedisTemplate) {
        return new MicroRedisRouteDefinitionRepository(livkReactiveRedisTemplate);
    }

    @Bean
    @ConditionalOnProperty(prefix = MicroRouteProperties.PREFIX, name = "type", havingValue = "REDIS_STR")
    public RouteDefinitionRepository redisRouteDefinitionRepository(
            ReactiveRedisTemplate<String, RouteDefinition> reactiveRedisTemplate) {
        return new RedisRouteDefinitionRepository(reactiveRedisTemplate);
    }

    @Bean
    @ConditionalOnBean(MicroReactiveRedisTemplate.class)
    public RedisRouteHealthIndicator redisRouteHealthIndicator(MicroReactiveRedisTemplate reactiveRedisTemplate) throws Exception {
        ThrowException.isTrue(o -> Boolean.FALSE
                        .equals(reactiveRedisTemplate.hasKey(MicroRedisRouteDefinitionRepository.ROUTE_KEY).block()))
                .throwException(new RouteCheckException("route initialization not detected"));
        return new RedisRouteHealthIndicator(reactiveRedisTemplate);
    }
}
