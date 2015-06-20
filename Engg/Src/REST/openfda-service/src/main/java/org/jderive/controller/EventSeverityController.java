package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.EventSeverityDomain;
import org.jderive.dto.EventSeverityDTO;
import org.jderive.service.EventSeverityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Durga on 6/20/2015.
 */

@RestController
@RequestMapping(value = "/eventseverity", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventSeverityController {

    @Autowired
    private EventSeverityService eventSeverityService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) {
        EventSeverityDomain eventSeverityDomain = eventSeverityService.findById(id);
        if (eventSeverityDomain != null) {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withEventSeverityList(ImmutableList.of(EventSeverityDTO.eventSeverity(eventSeverityDomain)))
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString()).build(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() {
        List<EventSeverityDomain> eventSeverityDomains = eventSeverityService.findAll();

        return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                .withStatusCode(HttpStatus.OK.toString())
                .withEventSeverityList(eventSeverityDomains.stream()
                        .map(eventSeverityDomain -> EventSeverityDTO.eventSeverity(eventSeverityDomain))
                        .collect(Collectors.toList())).build(),
                HttpStatus.OK);

    }
}
