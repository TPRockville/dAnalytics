package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Durga on 6/23/2015.
 */
@Setter @Getter
public class DrugSummaryByMonth {

    private Date startDate;

    private Long eventCount;

    public void setStartDate(Date startDate) {
        this.startDate = startDate != null ? (Date) startDate.clone() : null;
    }

    public Date getStartDate() {
        return startDate != null ? (Date) startDate.clone() : null;
    }
}
