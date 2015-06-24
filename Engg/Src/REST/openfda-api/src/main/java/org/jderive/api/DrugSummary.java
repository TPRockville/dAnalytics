package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Durga on 6/21/2015.
 */
@Setter
@Getter
public class DrugSummary {

    private Date startDate;
    private Long eventCount;

    public void setEventDate(Date eventDate) {
        this.startDate = eventDate != null ? (Date) startDate.clone() : null;
    }

    public Date getEventDate() {
        return startDate != null ? (Date) startDate.clone() : null;
    }
}
