package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Durga on 6/23/2015.
 */

@Setter @Getter
public class DrugCharSummary {

    private String id;
    private String drugId;
    private String characterizationId;
    private String eventCount;

}