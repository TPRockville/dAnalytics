package org.jderive.dto;

import org.jderive.api.EventSeverity;
import org.jderive.domain.EventSeverityDomain;
import org.springframework.beans.BeanUtils;

/**
 * Created by Durga on 6/20/2015.
 */
public final class EventSeverityDTO {

    private EventSeverityDTO() {

    }

    public static EventSeverity eventSeverity(EventSeverityDomain eventSeverityDomain) {
        EventSeverity eventSeverity = new EventSeverity();
        BeanUtils.copyProperties(eventSeverityDomain, eventSeverity);
        return eventSeverity;
    }
}
