package org.jderive.dto;

import org.jderive.api.Drug;
import org.jderive.api.DrugCharSummary;
import org.jderive.api.DrugEventSpike;
import org.jderive.api.DrugReactionSummary;
import org.jderive.api.DrugSummary;
import org.jderive.api.DrugSummaryByMonth;
import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.springframework.beans.BeanUtils;

/**
 * Created by Durga on 6/21/2015.
 */
public final class DrugDTO {

    private DrugDTO() {

    }

    public static Drug drug(DrugDomain drugDomain) {
        Drug drug = new Drug();
        BeanUtils.copyProperties(drugDomain, drug);
        return drug;
    }

    public static DrugSummary drugSummary(DrugSummaryDomain drugSummaryDomain) {
        DrugSummary drugSummary = new DrugSummary();
        drugSummary.setEventCount(drugSummaryDomain.getEventCount());
        drugSummary.setEventDate(drugSummaryDomain.getStartDate().toString());
        return drugSummary;
    }

    public static DrugEventSpike drugEventSpike(DrugEventSpikeDomain drugEventSpikeDomain) {
        DrugEventSpike drugEventSpike = new DrugEventSpike();
        BeanUtils.copyProperties(drugEventSpikeDomain, drugEventSpike);
        return drugEventSpike;
    }

    public static DrugCharSummary drugCharSummary(DrugCharSummaryDomain drugCharSummaryDomain) {
        DrugCharSummary drugCharSummary = new DrugCharSummary();
        BeanUtils.copyProperties(drugCharSummaryDomain, drugCharSummary);
        return drugCharSummary;
    }

    public static DrugReactionSummary drugReactionSummary(DrugReactionSummaryDomain drugReactionSummaryDomain) {
        DrugReactionSummary drugReactionSummary = new DrugReactionSummary();
        drugReactionSummary.setId(drugReactionSummaryDomain.getId());
        drugReactionSummary.setDrugId(drugReactionSummaryDomain.getDrugId());
        drugReactionSummary.setEventCount(drugReactionSummaryDomain.getEventCount());
        drugReactionSummary.setReactionName(drugReactionSummaryDomain.getReactionDomain() != null ?
                drugReactionSummaryDomain.getReactionDomain().getCode() : null);
        return drugReactionSummary;
    }

    public static DrugSummaryByMonth drugMonthSummary(DrugMonthSummaryDomain dbDrugMonthSummaryDomain) {
        DrugSummaryByMonth drugSummaryByMonth = new DrugSummaryByMonth();
        BeanUtils.copyProperties(dbDrugMonthSummaryDomain, drugSummaryByMonth);
        return drugSummaryByMonth;
    }
}