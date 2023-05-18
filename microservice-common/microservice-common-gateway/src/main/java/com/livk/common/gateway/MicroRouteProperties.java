package com.livk.common.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * LivkRouteProperties
 * </p>
 *
 * @author livk
 */
@Data
@ConfigurationProperties(prefix = MicroRouteProperties.PREFIX)
public class MicroRouteProperties {

    public static final String PREFIX = "spring.cloud.gateway.route";

    private RouteType type = RouteType.IN_MEMORY;

    enum RouteType {
        IN_MEMORY, REDIS_STR, REDIS_HASH
    }
}
