package org.jderive.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import org.jderive.api.AgeGroup;
import org.jderive.api.Gender;
import org.jderive.api.WeightGroup;
import org.jderive.domain.AgeGroupDomain;
import org.jderive.domain.WeightGroupDomain;
import org.jderive.dto.AgeGroupDTO;
import org.jderive.dto.WeightGroupDTO;
import org.jderive.service.AgeGroupService;
import org.jderive.service.WeightGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Pradeep on 6/25/2015.
 */
@Component
public final class CacheUtil {

    private static AgeGroupService ageGroupService;

    private static WeightGroupService weightGroupService;

    private static Map<Long,AgeGroup> ageGroupCache = new HashMap<Long,AgeGroup>();
    private static Map<Long,WeightGroup> weightGroupCache = new HashMap<Long,WeightGroup>();
    private static Map<Long,Gender> genderCache = new HashMap<Long,Gender>();

    @Autowired
    public void setAgeGroupService(AgeGroupService ageGroupService)
    {
        CacheUtil.ageGroupService = ageGroupService;
    }

    @Autowired
    public void setWeightGroupService(WeightGroupService weightGroupService)
    {
        CacheUtil.weightGroupService = weightGroupService;
    }

    private  CacheUtil(){}

    public static void addAgeGroupToCache(AgeGroupDomain... ageGroupDomains)
    {
        for (AgeGroupDomain ageGroupDomain : ageGroupDomains){
            ageGroupCache.put(ageGroupDomain.getId(),AgeGroupDTO.ageGroup(ageGroupDomain));
        }
    }
    public static String getAgeGroup(Long id)
    {
        if(ageGroupCache.size()==0)
        {
            List<AgeGroupDomain> ageGroupDomains = ageGroupService.findAll();
            CacheUtil.addAgeGroupToCache(ageGroupDomains.toArray(new AgeGroupDomain[ageGroupDomains.size()]));
        }
       return ageGroupCache.get(id).getName();
    }

    public static void addWeightGroupToCache(WeightGroupDomain... weightGroupDomains)
    {
        for (WeightGroupDomain weightGroupDomain : weightGroupDomains){
            weightGroupCache.put(weightGroupDomain.getId(), WeightGroupDTO.weightGroup(weightGroupDomain));
        }
    }
    public static String getWeightGroup(Long id)
    {
        if(weightGroupCache.size()==0)
        {
            List<WeightGroupDomain> weightGroupDomains = weightGroupService.findAll();
            CacheUtil.addWeightGroupToCache(weightGroupDomains.toArray(new WeightGroupDomain[weightGroupDomains.size()]));
        }
        return weightGroupCache.get(id).getName();
    }

    private static void addGenderToCache()
    {
        genderCache.put(0L, new Gender(0,"Not Specified"));
        genderCache.put(1L, new Gender(1,"Female"));
        genderCache.put(2L, new Gender(2,"Male"));
    }

    public static String getGender(Long id)
    {
        if(genderCache.size()==0)
            CacheUtil.addGenderToCache();
        return genderCache.get(id)!=null?genderCache.get(id).getName():genderCache.get(0L).getName();
    }
}
