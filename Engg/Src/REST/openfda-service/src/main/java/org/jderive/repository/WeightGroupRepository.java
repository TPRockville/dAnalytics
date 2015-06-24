package org.jderive.repository;

import org.jderive.domain.WeightGroupDomain;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
public interface WeightGroupRepository {

    List<WeightGroupDomain> findAll();

    WeightGroupDomain findById(Long id);
}
