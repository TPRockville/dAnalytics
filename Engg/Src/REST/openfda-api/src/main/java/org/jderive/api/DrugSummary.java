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

    private Date eventDate;
    private Long eventCount;

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate != null ? (Date) eventDate.clone() : null;
    }

    public Date getEventDate() {
        return eventDate != null ? (Date) eventDate.clone() : null;
    }
}
