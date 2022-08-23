package com.livk.microservice.gateway;

import com.livk.common.core.SpringBoot;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * MicroServiceGateway
 * </p>
 *
 * @author livk
 * @date 2022/8/12
 */
@SpringBootApplication
public class MicroServiceGateway {
    public static void main(String[] args) {
        SpringBoot.run(MicroServiceGateway.class, args);
    }
}
