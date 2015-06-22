package org.jderive.service.impl;

import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.repository.DrugRepository;
import org.jderive.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugRepository drugRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DrugDomain> findAll() {
        return drugRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DrugDomain findById(String id) {
        return drugRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummary) {
        return drugRepository.summary(drugSummary);
    }
}
