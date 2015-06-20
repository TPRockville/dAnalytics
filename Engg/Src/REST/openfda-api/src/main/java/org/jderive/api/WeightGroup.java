package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Durga on 6/20/2015.
 */
@Setter
@Getter
public class WeightGroup {

    private String id;
    private String name;
    private int minWeight;
    private int maxWeight;
}
