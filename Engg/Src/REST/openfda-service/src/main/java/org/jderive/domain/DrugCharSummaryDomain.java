package org.jderive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Durga on 6/23/2015.
 */
@Entity
@Table(name = "DRUG_CHAR_SUMMARY")
@Setter @Getter
public class DrugCharSummaryDomain {

    @Id
    @Column(name = "DRUG_CHAR_SUMMARY_ID")
    private String id;

    @Column(name = "DRUG_ID")
    private String drugId;

    @Column(name = "DRUG_CHARACTERIZATION_ID")
    private String characterizationId;

    @Column(name = "EVENT_COUNT")
    private String eventCount;
}
