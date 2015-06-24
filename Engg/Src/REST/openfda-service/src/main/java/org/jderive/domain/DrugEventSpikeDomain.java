package org.jderive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Durga on 6/23/2015.
 */

@Entity
@Table(name = "DRUG_SPIKES")
@Setter @Getter
public class DrugEventSpikeDomain {

    @Id
    @Column(name = "SPIKE_ID")
    private Long id;


    @Column(name = "DRUG_ID")
    private Long drugId;

    @Column(name = "SPIKE_DATE")
    private Date spikeDate;

    @Column(name = "EVENT_COUNT")
    private String eventCount;

    @Column(name = "DRUG_EVENT_AVG")
    private String eventAverage;

    @Column(name = "DRUG_EVENT_THRESHOLD")
    private String eventThresshold;

    public void setSpikeDate(Date spikeDate) {
        this.spikeDate = spikeDate != null ? (Date) spikeDate.clone() : null;
    }

    public Date getSpikeDate() {
        return spikeDate != null ? (Date) spikeDate.clone() : null;
    }
}
