package org.jderive.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jderive.api.Dimension;
import org.jderive.domain.DrugMonthSummaryDomain;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DimensionDTO {

	public static Map<String, List<Dimension>> transform(
			List<DrugMonthSummaryDomain> drugSummaryList) {
		Map<String, List<Dimension>> dimensionResponseMap = Maps.newHashMap();
		Map<DimensionType, Set<Long>> dimensionsSet = Maps.newHashMap();
		populateDimensionSet(dimensionsSet, drugSummaryList);
		populateAgeGroupDimension(dimensionsSet, drugSummaryList, dimensionResponseMap);
		populateWeightGroupDimension(dimensionsSet, drugSummaryList, dimensionResponseMap);
		populateGenderDimension(dimensionsSet, drugSummaryList, dimensionResponseMap);
		return dimensionResponseMap;
	}

    private static void populateDimensionSet(Map<DimensionType, Set<Long>> dimensionsSetMap,
            List<DrugMonthSummaryDomain> drugSummaryList) {
        Set<Long> genderGroupSet = Sets.newHashSet();
        Set<Long> weightGroupSet = Sets.newHashSet();
        Set<Long> ageGroupSet = Sets.newHashSet();

        for (DrugMonthSummaryDomain drugMonthSummaryDomain : drugSummaryList) {
            genderGroupSet.add(drugMonthSummaryDomain.getGenderId());
            weightGroupSet.add(drugMonthSummaryDomain.getWeightGroupId());
            ageGroupSet.add(drugMonthSummaryDomain.getAgeGroupId());
        }
        dimensionsSetMap.put(DimensionType.AGE_GROUP, ageGroupSet);
        dimensionsSetMap.put(DimensionType.WEIGHT_GROUP, weightGroupSet);
        dimensionsSetMap.put(DimensionType.GENDER_GROUP, genderGroupSet);

    }

    private static void populateGenderDimension(Map<DimensionType, Set<Long>> dimensionsSet, List<DrugMonthSummaryDomain> drugSummaryList,
            Map<String, List<Dimension>> dimensionResponseMap) {
        Set<Long> genderGroupSet = dimensionsSet.get(DimensionType.GENDER_GROUP);
        List<Dimension> genderDimensionList = Lists.newArrayList();
        for (Long genderGroupId : genderGroupSet) {
            Long eventCount = findEventCountSumFor(genderGroupId, drugSummaryList, DimensionType.GENDER_GROUP);
            Dimension dimension = new Dimension(genderGroupId, eventCount);
            genderDimensionList.add(dimension);
        }
        dimensionResponseMap.put("genderGroup", genderDimensionList);
    }

    private static void populateWeightGroupDimension(Map<DimensionType, Set<Long>> dimensionsSetMap, List<DrugMonthSummaryDomain> drugSummaryList,
            Map<String, List<Dimension>> dimensionResponseMap) {
        Set<Long> weightGroupSet = dimensionsSetMap.get(DimensionType.WEIGHT_GROUP);
        List<Dimension> weightDimensionList = Lists.newArrayList();
        for (Long weightGroupId : weightGroupSet) {
            Long eventCount = findEventCountSumFor(weightGroupId, drugSummaryList, DimensionType.WEIGHT_GROUP);
            Dimension dimension = new Dimension(weightGroupId, eventCount);
            weightDimensionList.add(dimension);
        }
        dimensionResponseMap.put("weightGroup", weightDimensionList);
    }

    private static void populateAgeGroupDimension(Map<DimensionType, Set<Long>> dimensionsSet,
			List<DrugMonthSummaryDomain> drugSummaryList,
			Map<String, List<Dimension>> dimensionResponseMap) {
		Set<Long> ageGroupSet = dimensionsSet.get(DimensionType.AGE_GROUP);
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
            	
                if(drugMonthSummaryDomain.getGenderId() != null && drugMonthSummaryDomain.getGenderId().equals(dimensionId) && drugMonthSummaryDomain.getEventCount()!= null) {
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
