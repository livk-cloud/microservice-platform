package com.livk.microservice.gateway;

import com.livk.common.core.Spring;
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
        Spring.run(MicroServiceGateway.class, args);
    }
}
