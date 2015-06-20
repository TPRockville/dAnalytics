package org.jderive.service.impl;

import org.jderive.domain.WeightGroupDomain;
import org.jderive.repository.WeightGroupRepository;
import org.jderive.service.WeightGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
@Service
public class WeightGroupServiceImpl implements WeightGroupService {

    @Autowired
    private WeightGroupRepository weightGroupRepository;

    @Override
    public List<WeightGroupDomain> findAll() {
        return weightGroupRepository.findAll();
    }

    @Override
    public WeightGroupDomain findById(String id) {
        return weightGroupRepository.findById(id);
    }
}
