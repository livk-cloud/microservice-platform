package com.livk.common.gateway.exception;

/**
 * <p>
 * RouteCheckException
 * </p>
 *
 * @author livk
 * @date 2022/3/14
 */
public class RouteCheckException extends RuntimeException {

    public RouteCheckException() {
        super();
    }

    public RouteCheckException(String message) {
        super(message);
    }

    public RouteCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteCheckException(Throwable cause) {
        super(cause);
    }

    public RouteCheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
