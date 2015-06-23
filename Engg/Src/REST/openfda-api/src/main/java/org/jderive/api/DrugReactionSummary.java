package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Durga on 6/23/2015.
 */
@Setter @Getter
public class DrugReactionSummary {

    private String id;

    private String drugId;

    private String reactionName;

    private Long eventCount;
}
