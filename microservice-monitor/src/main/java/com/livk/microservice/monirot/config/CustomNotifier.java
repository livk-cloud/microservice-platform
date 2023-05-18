package com.livk.microservice.monirot.config;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;

/**
 * <p>
 * CustomNotifier
 * </p>
 *
 * @author livk
 */
@Slf4j
@Component
public class CustomNotifier extends AbstractEventNotifier {

    protected CustomNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Nonnull
    @Override
    protected Mono<Void> doNotify(@Nonnull InstanceEvent event, @Nonnull Instance instance) {
        return Mono.fromRunnable(() -> {
            if (event instanceof InstanceStatusChangedEvent statusChangedEvent) {
                log.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
                        statusChangedEvent.getStatusInfo().getStatus());
            } else {
                log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }

}
