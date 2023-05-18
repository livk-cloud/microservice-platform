package com.livk.microservice.gateway;

import com.livk.commons.spring.SpringLauncher;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * MicroServiceGateway
 * </p>
 *
 * @author livk
 */
@SpringBootApplication
public class MicroServiceGateway {
    public static void main(String[] args) {
        SpringLauncher.run(MicroServiceGateway.class, args);
    }
}
