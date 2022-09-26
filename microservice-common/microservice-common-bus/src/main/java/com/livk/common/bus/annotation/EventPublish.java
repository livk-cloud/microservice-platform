package com.livk.common.bus.annotation;

import com.livk.common.core.constant.ServiceType;

import java.lang.annotation.*;

/**
 * <p>
 * EventPublish
 * </p>
 *
 * @author livk
 * @date 2022/09/22
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventPublish {

    ServiceType value() default ServiceType.MICRO_NONE;

    int port() default 0;

    AspectType type() default AspectType.AFTER;

    String expression() default "";

    enum AspectType {
        BEFORE,
        AFTER
    }

}
