package com.livk.common.gateway;

import com.livk.common.gateway.constant.RouteType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * LivkRouteProperties
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@Data
@ConfigurationProperties(prefix = MicroRouteProperties.PREFIX)
public class MicroRouteProperties {

    public static final String PREFIX = "spring.gateway.route";

    private RouteType type = RouteType.IN_MEMORY;

}
