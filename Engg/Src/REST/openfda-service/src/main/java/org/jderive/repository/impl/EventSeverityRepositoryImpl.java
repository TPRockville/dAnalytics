package org.jderive.repository.impl;

import org.jderive.domain.EventSeverityDomain;
import org.jderive.repository.EventSeverityRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
@Repository
public class EventSeverityRepositoryImpl implements EventSeverityRepository {

    @Override
    public List<EventSeverityDomain> findAll() {
        return populateEventSeverityInfo();
    }

    @Override
    public EventSeverityDomain findById(String id) {
        return populateEventSeverityInfo().stream()
                .filter(eventSeverityDomain -> eventSeverityDomain.getId().equalsIgnoreCase(id))
                .findFirst().get();
    }

    private List<EventSeverityDomain> populateEventSeverityInfo() {
        List<EventSeverityDomain> eventSeverityList = new ArrayList<>();
        EventSeverityDomain eventSeverity = new EventSeverityDomain();
        eventSeverity.setId("1");
        eventSeverity.setCode("Critical");
        eventSeverity.setDescription("critical condition.");
        eventSeverityList.add(eventSeverity);

        eventSeverity = new EventSeverityDomain();
        eventSeverity.setId("2");
        eventSeverity.setCode("Normal");
        eventSeverity.setDescription("normal condition.");
        eventSeverityList.add(eventSeverity);

        eventSeverity = new EventSeverityDomain();
        eventSeverity.setId("3");
        eventSeverity.setCode("Serious");
        eventSeverity.setDescription("serious condition.");
        eventSeverityList.add(eventSeverity);

        return eventSeverityList;
    }
}
