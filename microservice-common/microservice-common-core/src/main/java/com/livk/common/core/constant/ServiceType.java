package com.livk.common.core.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * ServiceConstant
 * </p>
 *
 * @author livk
 * @date 2022/9/26
 */
@Getter
@RequiredArgsConstructor
public enum ServiceType {

    MICRO_NONE(""),
    MICRO_AUTH("microservice-auth"),
    MICRO_GATEWAY("microservice-gateway"),
    MICRO_MONITOR("microservice-monitor");

    private final String serviceName;
}
