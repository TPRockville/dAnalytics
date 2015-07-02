package org.jderive.service;

import org.jderive.domain.*;
import org.jderive.dto.DrugReactionSummaryDTO;
import org.jderive.exception.JDeriveException;

import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
public interface DrugService {

    List<DrugDomain> findAll();

    DrugDomain findById(Long id);

    List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummary) throws JDeriveException;

    List<DrugDomain> findByName(String name,boolean containsFlag);

    List<DrugEventSpikeDomain> eventSpikeCount(Long drugId);

    List<DrugCharSummaryDomain> characterSummary(Long drugId);

    List<DrugReactionSummaryDTO> reactionSummary(Long drugId,int firstResult, int maxResults);

    List<DrugMonthSummaryDomain> summaryMonth(DrugMonthSummaryDomain drugMonthSummaryDomain, boolean applyProjection) throws JDeriveException;
	
	List<ERSummaryDomain> getERSummary(String drugId);

	List<DischargeSummaryDomain> getDischargeSummary(String drugId);

    List<DrugOnlyMonthSummaryDomain> drugOnlySummaryMonth(Long drugId) throws JDeriveException;

	List<Object[]> getTopOrBottomDrugs(int drugcount, String order);

	DrugDomain findByExactName(String drugName);

}
