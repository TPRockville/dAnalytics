package org.jderive.dto;

import org.jderive.api.DischargeSummary;
import org.jderive.domain.DischargeSummaryDomain;
import org.springframework.beans.BeanUtils;

public class DischargeSummaryDTO {

	public static DischargeSummary dischargeSummary(DischargeSummaryDomain dischargeSummaryDomain) {
		DischargeSummary dischargeSummary = new DischargeSummary();
        BeanUtils.copyProperties(dischargeSummaryDomain, dischargeSummary);
        return dischargeSummary;
    }
}
