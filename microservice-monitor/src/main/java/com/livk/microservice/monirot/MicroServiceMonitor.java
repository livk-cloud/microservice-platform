package com.livk.microservice.monirot;

import com.livk.commons.spring.SpringLauncher;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * MicroServiceMonitor
 * </p>
 *
 * @author livk
 */
@EnableAdminServer
@SpringBootApplication
public class MicroServiceMonitor {
    public static void main(String[] args) {
        SpringLauncher.run(MicroServiceMonitor.class, args);
    }
}
