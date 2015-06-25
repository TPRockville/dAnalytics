package org.jderive.dto;

import org.jderive.api.ERSummary;
import org.jderive.domain.ERSummaryDomain;
import org.springframework.beans.BeanUtils;

public class ERSummaryDTO {

	public static ERSummary erSummary(ERSummaryDomain erSummaryDomain) {
		ERSummary eRSummary = new ERSummary();
        BeanUtils.copyProperties(erSummaryDomain, eRSummary);
        return eRSummary;
    }
}
