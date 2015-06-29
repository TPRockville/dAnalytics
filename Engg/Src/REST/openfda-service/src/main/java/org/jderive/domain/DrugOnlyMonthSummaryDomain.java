package org.jderive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pradeep on 6/29/2015.
 */

@Entity
@Table(name = "DRUG_ONLY_SUMMARY_MONTH")
@Setter
@Getter
public class DrugOnlyMonthSummaryDomain {

    @Transient
    public static final String CACHE = "eventcount";

    @Id
    @Column(name = "DRUG_ONLY_SUMMARY_MONTH_ID")
    private Long id;

    @Column(name = "DRUG_ID")
    private Long drugId;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "EVENT_COUNT")
    private Long eventCount;

    public void setStartDate(Date startDate) {
        this.startDate = startDate != null ? (Date) startDate.clone() : null;
    }

    public Date getStartDate() {
        return startDate != null ? (Date) startDate.clone() : null;
    }

    public Date getEndDate() {
        return endDate != null ? (Date) endDate.clone() : null;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate != null ? (Date) endDate : null;
    }

}
