package com.livk.common.core;

import lombok.experimental.UtilityClass;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.AnnotationConfigurationException;

/**
 * <p>
 * MicroserviceSpring
 * </p>
 *
 * @author livk
 * @date 2022/8/12
 */
@UtilityClass
public class Spring {

    private SpringApplication application;

    public <T> ConfigurableApplicationContext run(Class<T> targetClass, String[] args) {
        if (!targetClass.isAnnotationPresent(SpringBootApplication.class)) {
            throw new AnnotationConfigurationException("must use @SpringBootApplication in start class");
        }
        SpringApplicationBuilder builder = new SpringApplicationBuilder(targetClass).banner(MicroserviceBanner.create())
                .bannerMode(Banner.Mode.CONSOLE);
        application = builder.application();
        return builder.run(args);
    }

    public SpringApplication application() {
        return application;
    }
}
