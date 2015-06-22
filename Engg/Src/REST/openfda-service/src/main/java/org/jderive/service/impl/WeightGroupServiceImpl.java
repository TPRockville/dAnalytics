package org.jderive.service.impl;

import org.jderive.domain.WeightGroupDomain;
import org.jderive.repository.WeightGroupRepository;
import org.jderive.service.WeightGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
@Service
public class WeightGroupServiceImpl implements WeightGroupService {

    @Autowired
    private WeightGroupRepository weightGroupRepository;

    @Override
    @Transactional(readOnly = true)
    public List<WeightGroupDomain> findAll() {
        return weightGroupRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public WeightGroupDomain findById(String id) {
        return weightGroupRepository.findById(id);
    }
}
