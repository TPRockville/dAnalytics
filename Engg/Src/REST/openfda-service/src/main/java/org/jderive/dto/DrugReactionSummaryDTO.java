package org.jderive.dto;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.jderive.api.Dimension;
import org.jderive.api.DrugReactionSummary;
import org.jderive.domain.DrugMonthSummaryDomain;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Pradeep on 6/26/2015.
 */
@Setter
@Getter
public class DrugReactionSummaryDTO {

    private Long id;

    private Long drugId;

    private String reactionName;

    private Long eventCount;

    public static DrugReactionSummary transform(
            DrugReactionSummaryDTO drugReactionSummaryDTO) {
        DrugReactionSummary drugReactionSummary = new DrugReactionSummary();
        drugReactionSummary.setId(drugReactionSummaryDTO.getId());
        drugReactionSummary.setDrugId(drugReactionSummaryDTO.getDrugId());
        drugReactionSummary.setReactionName(drugReactionSummaryDTO.getReactionName());
        drugReactionSummary.setEventCount(drugReactionSummaryDTO.getEventCount());
        return drugReactionSummary;
    }


}
