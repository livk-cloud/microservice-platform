package com.livk.common.bus.aspect;

import com.livk.common.bus.annotation.EventPublish;
import com.livk.common.bus.event.RouteRemoteEvent;
import com.livk.common.core.constant.ServiceType;
import com.livk.commons.aop.AnnotationAbstractPointcutTypeAdvisor;
import com.livk.commons.spring.context.SpringContextHolder;
import com.livk.commons.spring.spel.SpringExpressionResolver;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cloud.bus.BusProperties;
import org.springframework.core.env.Environment;

/**
 * <p>
 * RemoteAspect
 * </p>
 *
 * @author livk
 */
public class RemoteEventInterceptor extends AnnotationAbstractPointcutTypeAdvisor<EventPublish> {

    private final BusProperties busProperties;

    private final SpringExpressionResolver resolver;

    public RemoteEventInterceptor(BusProperties busProperties, Environment environment) {
        this.busProperties = busProperties;
        this.resolver = new SpringExpressionResolver(environment);
    }

    private String parsePublish(EventPublish eventPublish) {
        ServiceType serviceType = eventPublish.value();
        if (serviceType != ServiceType.MICRO_NONE) {
            StringBuilder destination = new StringBuilder(serviceType.getServiceName());
            if (eventPublish.port() > 0) {
                destination.append(":").append(eventPublish.port());
            } else {
                destination.append(":").append("**");
            }
            return destination.toString();
        } else {
            return eventPublish.expression();
        }
    }

    @Override
    protected Object invoke(MethodInvocation invocation, EventPublish eventPublish) throws Throwable {
        String destination = parsePublish(eventPublish);
        String value = resolver.evaluate(destination, invocation.getMethod(), invocation.getArguments());
        if (eventPublish.type() == EventPublish.AspectType.BEFORE) {
            SpringContextHolder.publishEvent(new RouteRemoteEvent(busProperties.getId(), () -> value));
        }
        Object proceed = invocation.proceed();
        if (eventPublish.type() == EventPublish.AspectType.AFTER) {
            SpringContextHolder.publishEvent(new RouteRemoteEvent(busProperties.getId(), () -> value));
        }
        return proceed;
    }
}
