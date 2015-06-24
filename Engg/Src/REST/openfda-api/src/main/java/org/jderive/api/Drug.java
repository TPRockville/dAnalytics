package org.jderive.api;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Durga on 6/21/2015.
 */

@Setter @Getter
public class Drug {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;
}
