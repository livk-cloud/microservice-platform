package com.livk.microservice.monirot;

import com.livk.common.core.SpringBoot;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * MicroServiceMonitor
 * </p>
 *
 * @author livk
 * @date 2022/8/23
 */
@EnableAdminServer
@SpringBootApplication
public class MicroServiceMonitor {
    public static void main(String[] args) {
        SpringBoot.run(MicroServiceMonitor.class, args);
    }
}
