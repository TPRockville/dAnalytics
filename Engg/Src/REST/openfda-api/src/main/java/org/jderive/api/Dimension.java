package org.jderive.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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

    @JsonSerialize(using = ToStringSerializer.class)
	private Long type;

    @JsonSerialize(using = ToStringSerializer.class)
	private Long eventCount;
}
