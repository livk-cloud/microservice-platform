package com.livk.common.bus.aspect;

import com.livk.common.bus.annotation.EventPublish;
import com.livk.common.bus.event.RouteRemoteEvent;
import com.livk.common.core.constant.ServiceType;
import com.livk.common.core.function.BranchHandle;
import com.livk.common.core.support.SpringContextHolder;
import com.livk.common.core.util.SpringUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * <p>
 * RemoteAspect
 * </p>
 *
 * @author livk
 * @date 2022/9/26
 */
@RequiredArgsConstructor
public class RemoteAspect {

    private final BusProperties busProperties;

    @Around("@annotation(eventPublish)")
    public Object publishEvent(ProceedingJoinPoint joinPoint, EventPublish eventPublish) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (eventPublish == null) {
            eventPublish = AnnotationUtils.findAnnotation(method, EventPublish.class);
            Assert.notNull(eventPublish, "LivkEventPublish is null");
        }
        String destination = parsePublish(eventPublish);
        String value = SpringUtils.parseSpEL(method, joinPoint.getArgs(), destination);
        if (eventPublish.type() == EventPublish.AspectType.BEFORE) {
            SpringContextHolder.publishEvent(new RouteRemoteEvent(busProperties.getId(), () -> value));
        }
        Object proceed = joinPoint.proceed();
        if (eventPublish.type() == EventPublish.AspectType.AFTER) {
            SpringContextHolder.publishEvent(new RouteRemoteEvent(busProperties.getId(), () -> value));
        }
        return proceed;
    }

    private String parsePublish(EventPublish eventPublish) {
        ServiceType serviceType = eventPublish.value();
        if (serviceType != ServiceType.MICRO_NONE) {
            StringBuffer destination = new StringBuffer(serviceType.getServiceName());
            BranchHandle.isTrueOrFalse(o -> eventPublish.port() > 0)
                    .trueOrFalseHandle(() -> destination.append(":").append(eventPublish.port()),
                            () -> destination.append(":").append("**"));
            return destination.toString();
        } else {
            return eventPublish.expression();
        }
    }
}
