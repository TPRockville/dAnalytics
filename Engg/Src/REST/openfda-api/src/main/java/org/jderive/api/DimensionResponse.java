package org.jderive.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

import org.jsondoc.core.annotation.ApiObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiObject(show = true, description = "Dimensions API Response")
public class DimensionResponse {

	private Map<String, List<Dimension>> dimensionResponse;
}
