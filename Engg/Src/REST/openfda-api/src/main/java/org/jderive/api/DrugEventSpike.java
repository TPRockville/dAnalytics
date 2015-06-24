package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by Durga on 6/23/2015.
 */
@Setter @Getter
public class DrugEventSpike {

    private Long id;

    private Long drugId;

    private Date spikeDate;

    private String eventCount;

    private String eventAverage;

    private String eventThresshold;

    public void setSpikeDate(Date spikeDate) {
        this.spikeDate = spikeDate != null ? (Date) spikeDate.clone() : null;
    }

    public Date getSpikeDate() {
        return spikeDate != null ? (Date) spikeDate.clone() : null;
    }
}
