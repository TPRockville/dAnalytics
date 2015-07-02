package org.jderive.service.impl;

import org.jderive.domain.*;
import org.jderive.dto.DrugReactionSummaryDTO;
import org.jderive.exception.JDeriveException;
import org.jderive.repository.DrugRepository;
import org.jderive.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
@Service
public class DrugServiceImpl implements DrugService {
    private static final String ALL_DRUG_COUNT_CACHE = "ALL_DRUG_COUNT_CACHE";

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private CacheManager cacheManager;


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
    public List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummary) throws JDeriveException {
        return drugRepository.summary(drugSummary);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugDomain> findByName(String name, boolean containsFlag) {

        if (containsFlag) {
            name = "%" + name;
        }

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
    public List<DrugReactionSummaryDTO> reactionSummary(Long drugId, int firstResult, int maxResults) {
        return drugRepository.reactionSummary(drugId, firstResult, maxResults);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugMonthSummaryDomain> summaryMonth(
            DrugMonthSummaryDomain drugSummary, boolean applyProjection) throws JDeriveException {
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

    @Transactional(readOnly = true)
    public List<ERSummaryDomain> getERSummary(String drugId) {
        return drugRepository.getERSummary(drugId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<DischargeSummaryDomain> getDischargeSummary(String drugId) {
        return drugRepository.getDischargeSummary(drugId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugOnlyMonthSummaryDomain> drugOnlySummaryMonth(Long drugId) throws JDeriveException {
        Cache cache = cacheManager.getCache(DrugOnlyMonthSummaryDomain.EVENT_COUNT_CACHE);

        if (drugId == null && cache.get(ALL_DRUG_COUNT_CACHE) != null) {
            return (List<DrugOnlyMonthSummaryDomain>) cache.get(ALL_DRUG_COUNT_CACHE).get();
        }
        List<DrugOnlyMonthSummaryDomain> drugOnlyMonthSummaryDomains = drugRepository.drugOnlySummaryMonth(drugId);
        if (drugId == null) {
            cache.put(ALL_DRUG_COUNT_CACHE, drugOnlyMonthSummaryDomains);
        }
        return drugOnlyMonthSummaryDomains;
    }

	@Override
	@Transactional(readOnly = true)
	public List<Object[]> getTopOrBottomDrugs(int drugcount, String order) {
		
		return drugRepository.getTopOrBottomDrugs(drugcount,order);
	}

	@Override
	@Transactional(readOnly = true)
	public DrugDomain findByExactName(String drugName) {
		return drugRepository.findByExactName(drugName);
	}
}
