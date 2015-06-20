package org.jderive.dto;

import org.jderive.api.WeightGroup;
import org.jderive.domain.WeightGroupDomain;
import org.springframework.beans.BeanUtils;

/**
 * Created by Durga on 6/20/2015.
 */
public final class WeightGroupDTO {

    private WeightGroupDTO() {

    }

    public static WeightGroup weightGroup(WeightGroupDomain weightGroupDomain) {
        WeightGroup weightGroup = new WeightGroup();
        BeanUtils.copyProperties(weightGroupDomain, weightGroup);
        return weightGroup;
    }
}
