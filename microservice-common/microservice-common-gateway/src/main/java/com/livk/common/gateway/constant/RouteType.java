package com.livk.common.gateway.constant;

import lombok.Getter;

/**
 * <p>
 * RouteType
 * </p>
 *
 * @author livk
 * @date 2021/12/6
 */
@Getter
public enum RouteType {

    /**
     *
     */
    IN_MEMORY, REDIS_STR, REDIS_HASH

}
