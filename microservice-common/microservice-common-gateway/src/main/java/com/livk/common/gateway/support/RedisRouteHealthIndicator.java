package com.livk.common.gateway.support;

import com.livk.common.redis.support.ReactiveRedisOps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractReactiveHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import reactor.core.publisher.Mono;

/**
 * <p>
 * RedisRouteHealthIndicator
 * </p>
 *
 * @author livk
 */
@Slf4j
@RequiredArgsConstructor
public class RedisRouteHealthIndicator extends AbstractReactiveHealthIndicator {

    private final ReactiveRedisOps reactiveRedisOps;

    @Override
    protected Mono<Health> doHealthCheck(Health.Builder builder) {
        return reactiveRedisOps.hasKey(MicroRedisRouteDefinitionRepository.ROUTE_KEY)
                .map(bool -> {
                    if (bool) {
                        return builder.up();
                    } else {
                        log.warn("Redis路由信息丢失！");
                        return builder.down();
                    }
                }).map(Health.Builder::build);
    }
}
