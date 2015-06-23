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
 * Created by Durga on 6/23/2015.
 */
@Entity
@Table(name = "DRUG_SUMMARY_MONTH")
public class DrugMonthSummaryDomain {
    @Id
    @Column(name = "DRUG_SUMMARY_MONTH_ID")
    @Setter
    @Getter
    private String id;

    @Column(name = "DRUG_ID")
    @Setter
    @Getter
    private String drugId;

    @Column(name = "DRUG_INDICATION")
    @Setter
    @Getter
    private String drugIndication;

    @Column(name = "DRUG_SUBSTANCE_ID")
    @Setter
    @Getter
    private String drugSubstanceId;

    @Column(name = "DRUG_REACTION_ID")
    @Setter
    @Getter
    private String drugReactionId;

    @Column(name = "COUNTRY_ID")
    @Setter
    @Getter
    private String countryId;

    @Column(name = "GENDER_ID")
    @Setter
    @Getter
    private String genderId;

    @Column(name = "AGE_GROUP_ID")
    @Setter
    @Getter
    private String ageGroupId;

    @Column(name = "WEIGHT_GROUP_ID")
    @Setter
    @Getter
    private String weightGroupId;

    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "EVENT_COUNT")
    @Setter
    @Getter
    private String eventCount;


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
