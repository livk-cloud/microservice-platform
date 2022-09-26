package com.livk.common.gateway.support;

import com.livk.common.core.function.Present;
import com.livk.common.redis.support.MicroReactiveRedisTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * <p>
 * RedisRouteHealthIndicator
 * </p>
 *
 * @author livk
 * @date 2021/11/3
 */
@Slf4j
@RequiredArgsConstructor
public class RedisRouteHealthIndicator extends AbstractHealthIndicator {

    private final MicroReactiveRedisTemplate reactiveRedisTemplate;

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        reactiveRedisTemplate.hasKey(MicroRedisRouteDefinitionRepository.ROUTE_KEY)
                .subscribe(exit -> Present.handler(exit, Boolean.TRUE::equals).present(bool -> builder.up(), () -> {
                    log.warn("Redis路由信息丢失！");
                    builder.down();
                }));
    }

}
