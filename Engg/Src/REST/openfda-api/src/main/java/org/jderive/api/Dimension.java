package org.jderive.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@ToString
public class Dimension {

	public Dimension(Long type, Long eventCount) {
		this.type = type;
		this.eventCount = eventCount;
	}

	private Long type;

	private Long eventCount;
}
