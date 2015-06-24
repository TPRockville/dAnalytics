package org.jderive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by Durga on 6/21/2015.
 */

@Entity
@Table(name = "DRUG_SUMMARY")
@Setter @Getter
public class DrugSummaryDomain {

    @Id
    @Column(name = "DRUG_SUMMARY_ID")
    private Long id;

    @Column(name = "DRUG_ID")
    private Long drugId;

    @Column(name = "AGE_GROUP_ID")
    private Long ageGroupId;

    @Column(name = "WEIGHT_GROUP_ID")
    private Long weightGroupId;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @Column(name = "GENDER_ID")
    private Long genderId;

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
