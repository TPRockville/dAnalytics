package org.jderive.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jderive.api.Dimension;
import org.jderive.api.DimensionResponse;
import org.jderive.domain.DrugMonthSummaryDomain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DimensionDTO {

	public static DimensionResponse transform(
			List<DrugMonthSummaryDomain> drugSummaryList) {
		DimensionResponse dimensionResponse = new DimensionResponse();
		Map<String, List<Dimension>> dimensionResponseMap = Maps.newHashMap();
		populateAgeGroupDimension(drugSummaryList, dimensionResponseMap);
		populateWeightGroupDimension(drugSummaryList, dimensionResponseMap);
		populateGenderDimension(drugSummaryList, dimensionResponseMap);
		dimensionResponse.setDimensionResponse(dimensionResponseMap);
		return dimensionResponse;
	}

	private static void populateGenderDimension(List<DrugMonthSummaryDomain> drugSummaryList,
            Map<String, List<Dimension>> dimensionResponseMap) {
        Set<Long> genderGroupSet = Sets.newHashSet();
        for (DrugMonthSummaryDomain drugMonthSummaryDomain : drugSummaryList) {
            genderGroupSet.add(drugMonthSummaryDomain.getGenderId());
        }
        List<Dimension> genderDimensionList = Lists.newArrayList();
        for (Long genderGroupId : genderGroupSet) {
            Long eventCount = findEventCountSumFor(genderGroupId, drugSummaryList, DimensionType.GENDER_GROUP);
            Dimension dimension = new Dimension(genderGroupId, eventCount);
            genderDimensionList.add(dimension);
        }
        dimensionResponseMap.put("genderGroup", genderDimensionList);
    }

    private static void populateWeightGroupDimension(List<DrugMonthSummaryDomain> drugSummaryList,
            Map<String, List<Dimension>> dimensionResponseMap) {
        Set<Long> weightGroupSet = Sets.newHashSet();
        for (DrugMonthSummaryDomain drugMonthSummaryDomain : drugSummaryList) {
            weightGroupSet.add(drugMonthSummaryDomain.getWeightGroupId());
        }
        List<Dimension> weightDimensionList = Lists.newArrayList();
        for (Long weightGroupId : weightGroupSet) {
            Long eventCount = findEventCountSumFor(weightGroupId, drugSummaryList, DimensionType.WEIGHT_GROUP);
            Dimension dimension = new Dimension(weightGroupId, eventCount);
            weightDimensionList.add(dimension);
        }
        dimensionResponseMap.put("weightGroup", weightDimensionList);
    }

    private static void populateAgeGroupDimension(
			List<DrugMonthSummaryDomain> drugSummaryList,
			Map<String, List<Dimension>> dimensionResponseMap) {
		Set<Long> ageGroupSet = Sets.newHashSet();
		for (DrugMonthSummaryDomain drugMonthSummaryDomain : drugSummaryList) {
			ageGroupSet.add(drugMonthSummaryDomain.getAgeGroupId());
		}
		List<Dimension> ageGroupDimensionList = Lists.newArrayList();
		for (Long ageGroupId : ageGroupSet) {
			Long eventCount = findEventCountSumFor(ageGroupId, drugSummaryList, DimensionType.AGE_GROUP);
			Dimension dimension = new Dimension(ageGroupId, eventCount);
			ageGroupDimensionList.add(dimension);
		}
		dimensionResponseMap.put("ageGroup", ageGroupDimensionList);
	}

	private static Long findEventCountSumFor(Long dimensionId,
			List<DrugMonthSummaryDomain> drugSummaryList, DimensionType dimensionType) {
		Long count = 0l;
		switch (dimensionType) {
        case AGE_GROUP:
            for (DrugMonthSummaryDomain drugMonthSummaryDomain : drugSummaryList) {
                if(drugMonthSummaryDomain.getAgeGroupId().equals(dimensionId) && drugMonthSummaryDomain.getEventCount()!= null) {
                    count = count + drugMonthSummaryDomain.getEventCount();
                }
            }
            break;
        case WEIGHT_GROUP:
            for (DrugMonthSummaryDomain drugMonthSummaryDomain : drugSummaryList) {
                if(drugMonthSummaryDomain.getWeightGroupId().equals(dimensionId) && drugMonthSummaryDomain.getEventCount()!= null) {
                    count = count + drugMonthSummaryDomain.getEventCount();
                }
            }
            break;
        case GENDER_GROUP:
            for (DrugMonthSummaryDomain drugMonthSummaryDomain : drugSummaryList) {
                if(drugMonthSummaryDomain.getGenderId().equals(dimensionId) && drugMonthSummaryDomain.getEventCount()!= null) {
                    count = count + drugMonthSummaryDomain.getEventCount();
                }
            }
            break;    
        default:
            break;
        }
		
		return count;
	}
	
	private enum DimensionType {
	    AGE_GROUP,
	    WEIGHT_GROUP,
	    GENDER_GROUP;
	}

}
