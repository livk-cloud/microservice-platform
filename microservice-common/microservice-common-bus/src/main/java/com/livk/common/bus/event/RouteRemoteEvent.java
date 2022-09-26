package com.livk.common.bus.event;

import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * <p>
 * RouteRemoteEvent
 * </p>
 *
 * @author livk
 * @date 2022/9/26
 */
public class RouteRemoteEvent extends RemoteApplicationEvent {

    public RouteRemoteEvent() {
        super();
    }

    public RouteRemoteEvent(String originService, Destination destination) {
        this("livk", originService, destination);
    }

    /**
     * {@see org.springframework.cloud.bus.event.RemoteApplicationEvent#RemoteApplicationEvent(Object,
     *String, String)}
     */
    public RouteRemoteEvent(Object source, String originService, Destination destination) {
        super(source, originService, destination);
    }

}
