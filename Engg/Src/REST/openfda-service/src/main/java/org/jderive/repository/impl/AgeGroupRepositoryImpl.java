package org.jderive.repository.impl;

import org.jderive.domain.AgeGroupDomain;
import org.jderive.repository.AgeGroupRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Repository
public class AgeGroupRepositoryImpl implements AgeGroupRepository {


    @Override
    public List<AgeGroupDomain> findAll() {
        return populateAgeGroupInfo();
    }

    @Override
    public AgeGroupDomain findById(String id) {
        return populateAgeGroupInfo().stream()
                .filter(ageGroupDomain -> ageGroupDomain.getId().equalsIgnoreCase(id))
                .findFirst().get();
    }

    private List<AgeGroupDomain> populateAgeGroupInfo() {
        List<AgeGroupDomain> ageGroupList = new ArrayList<>();
        AgeGroupDomain ageGroup = new AgeGroupDomain();
        ageGroup.setId("1");
        ageGroup.setName("0 to 20");
        ageGroup.setMinAge(0);
        ageGroup.setMaxAge(20);
        ageGroupList.add(ageGroup);
        ageGroup = new AgeGroupDomain();
        ageGroup.setId("2");
        ageGroup.setName("21 to 40");
        ageGroup.setMinAge(21);
        ageGroup.setMaxAge(40);
        ageGroupList.add(ageGroup);
        ageGroup = new AgeGroupDomain();
        ageGroup.setId("3");
        ageGroup.setName("41 to 60");
        ageGroup.setMinAge(41);
        ageGroup.setMaxAge(60);
        ageGroupList.add(ageGroup);
        ageGroup = new AgeGroupDomain();
        ageGroup.setId("4");
        ageGroup.setName("61 to 80");
        ageGroup.setMinAge(61);
        ageGroup.setMaxAge(80);
        ageGroupList.add(ageGroup);
        return ageGroupList;
    }
}
