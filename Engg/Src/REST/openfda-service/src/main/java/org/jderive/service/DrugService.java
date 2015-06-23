package org.jderive.service;

import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;

import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
public interface DrugService {

    List<DrugDomain> findAll();

    DrugDomain findById(String id);

    List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummary);

    List<DrugDomain> findByName(String name);

    List<DrugEventSpikeDomain> eventSpikeCount(String drugId);

    List<DrugCharSummaryDomain> characterSummary(String drugId);

    List<DrugReactionSummaryDomain> reactionSummary(String drugId);

    List<DrugMonthSummaryDomain> summaryMonth(DrugMonthSummaryDomain drugMonthSummaryDomain);
}
