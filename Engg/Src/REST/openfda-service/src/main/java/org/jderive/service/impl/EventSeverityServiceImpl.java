package org.jderive.service.impl;

import org.jderive.domain.EventSeverityDomain;
import org.jderive.repository.EventSeverityRepository;
import org.jderive.service.EventSeverityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Service
public class EventSeverityServiceImpl implements EventSeverityService {

    @Autowired
    private EventSeverityRepository eventSeverityRepository;

    @Override
    public List<EventSeverityDomain> findAll() {
        return eventSeverityRepository.findAll();
    }

    @Override
    public EventSeverityDomain findById(String id) {
        return eventSeverityRepository.findById(id);
    }
}
