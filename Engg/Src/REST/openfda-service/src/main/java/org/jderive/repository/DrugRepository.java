package org.jderive.repository;

import org.jderive.domain.DischargeSummaryDomain;
import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.domain.ERSummaryDomain;
import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
public interface DrugRepository {

    List<DrugDomain> findAll();

    DrugDomain findById(Long id);

    List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummaryDomain);

    List<DrugDomain> findByName(String name);

    List<DrugEventSpikeDomain> eventSpikeCount(Long drugId);

    List<DrugCharSummaryDomain> characterSummary(Long drugId);

    List<DrugReactionSummaryDomain>  reactionSummary(Long drugId);

    List<DrugMonthSummaryDomain> summaryMonth(DrugMonthSummaryDomain drugSummary, boolean applyProjection);
    
	List<ERSummaryDomain> getERSummary(String drugId);

	List<DischargeSummaryDomain> getDischargeSummary(String drugId);
}
