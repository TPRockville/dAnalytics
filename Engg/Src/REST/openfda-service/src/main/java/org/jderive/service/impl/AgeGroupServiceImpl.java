package org.jderive.service.impl;

import org.jderive.domain.AgeGroupDomain;
import org.jderive.repository.AgeGroupRepository;
import org.jderive.service.AgeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Service
public class AgeGroupServiceImpl implements AgeGroupService {

    @Autowired
    private AgeGroupRepository ageGroupRepository;

    @Override
    public List<AgeGroupDomain> findAll() {
        return ageGroupRepository.findAll();
    }

    @Override
    public AgeGroupDomain findById(String id) {
        return ageGroupRepository.findById(id);
    }
}
