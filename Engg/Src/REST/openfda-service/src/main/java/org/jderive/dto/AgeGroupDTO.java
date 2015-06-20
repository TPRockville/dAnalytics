package org.jderive.dto;

import org.jderive.api.AgeGroup;
import org.jderive.domain.AgeGroupDomain;
import org.springframework.beans.BeanUtils;

/**
 * Created by Durga on 6/20/2015.
 */
public final class AgeGroupDTO {

    private AgeGroupDTO() {

    }

    public static AgeGroup ageGroup(AgeGroupDomain ageGroupDomain) {
        AgeGroup ageGroup = new AgeGroup();
        BeanUtils.copyProperties(ageGroupDomain, ageGroup);
        return ageGroup;
    }
}
