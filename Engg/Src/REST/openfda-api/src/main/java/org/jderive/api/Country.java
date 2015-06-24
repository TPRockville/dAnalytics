package org.jderive.api;

import lombok.Getter;
import lombok.Setter;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * VO design pattern.
 *
 * Created by Durga on 6/20/2015.
 */

@Setter
@Getter
@ApiObject(show = true, description = "Project json structure.")
public class Country {

    @ApiObjectField(description = "specify the Country id.", required = false)
    private Long id;
    @ApiObjectField(description = "specify the Country name.", required = true)
    private String name;
    @ApiObjectField(description = "specify the Country Code.", required = true)
    private String code;
}
