package org.jderive.service;

import org.jderive.domain.AgeGroupDomain;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
public interface AgeGroupService {

    List<AgeGroupDomain> findAll();

    AgeGroupDomain findById(Long id);
}
