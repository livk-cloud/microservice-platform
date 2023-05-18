package com.livk.common.core.constant;

/**
 * <p>
 * CommonConstant
 * </p>
 *
 * @author livk
 */
public interface CommonConstant {
    Integer SUCCESS_CODE = 200;

    String SUCCESS_MSG = "success";

    Integer ERROR_CODE = 500;

    String MICRO_AUTH = ServiceType.MICRO_AUTH.getServiceName();

    String MICRO_GATEWAY = ServiceType.MICRO_GATEWAY.getServiceName();

    String MICRO_MONITOR = ServiceType.MICRO_MONITOR.getServiceName();
}
