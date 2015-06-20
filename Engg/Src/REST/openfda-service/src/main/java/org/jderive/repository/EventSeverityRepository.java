package org.jderive.repository;

import org.jderive.domain.EventSeverityDomain;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
public interface EventSeverityRepository {


    List<EventSeverityDomain> findAll();

    EventSeverityDomain findById(String id);
}
