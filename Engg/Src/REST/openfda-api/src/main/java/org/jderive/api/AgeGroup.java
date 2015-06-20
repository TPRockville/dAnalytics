package org.jderive.api;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Durga on 6/20/2015.
 */

@Setter
@Getter
public class AgeGroup {

    private String id;
    private String name;
    private int minAge;
    private int maxAge;
}
