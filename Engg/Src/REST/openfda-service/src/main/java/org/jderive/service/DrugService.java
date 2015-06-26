package org.jderive.service;

import org.jderive.domain.DischargeSummaryDomain;
import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.domain.ERSummaryDomain;
import org.jderive.dto.DrugReactionSummaryDTO;

import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
public interface DrugService {

    List<DrugDomain> findAll();

    DrugDomain findById(Long id);

    List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummary);

    List<DrugDomain> findByName(String name,boolean containsFlag);

    List<DrugEventSpikeDomain> eventSpikeCount(Long drugId);

    List<DrugCharSummaryDomain> characterSummary(Long drugId);

    List<DrugReactionSummaryDTO> reactionSummary(Long drugId);

    List<DrugMonthSummaryDomain> summaryMonth(DrugMonthSummaryDomain drugMonthSummaryDomain, boolean applyProjection);
	
	List<ERSummaryDomain> getERSummary(String drugId);

	List<DischargeSummaryDomain> getDischargeSummary(String drugId);

}
