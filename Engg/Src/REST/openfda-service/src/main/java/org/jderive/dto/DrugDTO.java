package org.jderive.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jderive.api.Drug;
import org.jderive.api.DrugCharSummary;
import org.jderive.api.DrugEventSpike;
import org.jderive.api.DrugReactionSummary;
import org.jderive.api.DrugSummary;
import org.jderive.api.DrugSummaryByMonth;
import org.jderive.domain.*;
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
        drugSummary.setStartDate(drugSummaryDomain.getStartDate());
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
        drugSummaryByMonth.setEventCount(dbDrugMonthSummaryDomain.getEventCount());
        try
        {
            if(dbDrugMonthSummaryDomain.getStartDate()!=null)
            {
                drugSummaryByMonth.setStartDate(dbDrugMonthSummaryDomain.getStartDate());
            }

        }catch(Exception exception)
        {
            System.out.println("Exception : "+exception.getMessage());
        }



        return drugSummaryByMonth;
    }

    public static DrugSummaryByMonth drugOnlyMonthSummary(DrugOnlyMonthSummaryDomain drugOnlyMonthSummaryDomain) {

        DrugSummaryByMonth drugSummaryByMonth = new DrugSummaryByMonth();
        drugSummaryByMonth.setEventCount(drugOnlyMonthSummaryDomain.getEventCount());
        try
        {
            if(drugOnlyMonthSummaryDomain.getStartDate()!=null)
            {
                drugSummaryByMonth.setStartDate(drugOnlyMonthSummaryDomain.getStartDate());
            }

        }catch(Exception exception)
        {
            System.out.println("Exception : "+exception.getMessage());
        }
        return drugSummaryByMonth;
    }
}
