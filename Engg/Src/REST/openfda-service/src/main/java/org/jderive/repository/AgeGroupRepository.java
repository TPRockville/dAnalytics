package org.jderive.repository;

import org.jderive.domain.AgeGroupDomain;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
public interface AgeGroupRepository {

    List<AgeGroupDomain> findAll();

    AgeGroupDomain findById(Long id);
}
