package org.jderive.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.repository.DrugRepository;
import org.jderive.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public DrugDomain findById(Long id) {
        return drugRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummary) {
        return drugRepository.summary(drugSummary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugDomain> findByName(String name) {
        return drugRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugEventSpikeDomain> eventSpikeCount(Long drugId) {
        return drugRepository.eventSpikeCount(drugId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugCharSummaryDomain> characterSummary(Long drugId) {
        return drugRepository.characterSummary(drugId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugReactionSummaryDomain> reactionSummary(Long drugId) {
        List<DrugReactionSummaryDomain> drugReactionSummaryDomainList = drugRepository.reactionSummary(drugId);
        //Aggregate the DrugReactionSummaryDomain from 2 index to list.size();
        if (CollectionUtils.isNotEmpty(drugReactionSummaryDomainList)) {

            if (drugReactionSummaryDomainList.size() > 2) {
                List<DrugReactionSummaryDomain> finalDrugReactionSummaryDomainsList = new ArrayList<>();
                //Loading on demand.
                drugReactionSummaryDomainList.get(0).getReactionDomain().getCode();
                drugReactionSummaryDomainList.get(1).getReactionDomain().getCode();

                DrugReactionSummaryDomain aggregatedReactionSummary = aggregateReactionSummaryDomains(
                        drugReactionSummaryDomainList.subList(2, drugReactionSummaryDomainList.size()), drugId);

                finalDrugReactionSummaryDomainsList.add(drugReactionSummaryDomainList.get(0));
                finalDrugReactionSummaryDomainsList.add(drugReactionSummaryDomainList.get(1));
                finalDrugReactionSummaryDomainsList.add(aggregatedReactionSummary);
                return finalDrugReactionSummaryDomainsList;
            } else {
                for (DrugReactionSummaryDomain domain : drugReactionSummaryDomainList) {
                    domain.getReactionDomain();
                }
                return drugReactionSummaryDomainList;
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugMonthSummaryDomain> summaryMonth(
            DrugMonthSummaryDomain drugSummary, boolean applyProjection) {
        return drugRepository.summaryMonth(drugSummary, applyProjection);
    }

    private DrugReactionSummaryDomain aggregateReactionSummaryDomains(List<DrugReactionSummaryDomain>
                                                                      drugReactionSummaryDomainList, Long drugId) {
        Long sumOfEventCounts = drugReactionSummaryDomainList.stream()
                .mapToLong(DrugReactionSummaryDomain::getEventCount).sum();
        DrugReactionSummaryDomain drugReactionSummaryDomain = new DrugReactionSummaryDomain();
        drugReactionSummaryDomain.setEventCount(sumOfEventCounts);
        DrugReactionDomain drugReactionDomain = new DrugReactionDomain();
        drugReactionDomain.setCode("Others");
        drugReactionSummaryDomain.setReactionDomain(drugReactionDomain);
        drugReactionSummaryDomain.setDrugId(drugId);
        return drugReactionSummaryDomain;
    }
}
