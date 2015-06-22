package org.jderive.dto;

import org.jderive.api.Drug;
import org.jderive.api.DrugSummary;
import org.jderive.domain.DrugDomain;
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
}
