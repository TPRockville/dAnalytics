package org.jderive.repository.impl;

import org.jderive.domain.WeightGroupDomain;
import org.jderive.repository.WeightGroupRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
@Repository
public class WeightGroupRepositoryImpl implements WeightGroupRepository {

    @Override
    public List<WeightGroupDomain> findAll() {
        return populateWeightGroupInfo();
    }

    @Override
    public WeightGroupDomain findById(String id) {
        return populateWeightGroupInfo().stream()
                .filter(weightGroupDomain -> weightGroupDomain.getId().equalsIgnoreCase(id))
                .findFirst().get();
    }

    private List<WeightGroupDomain> populateWeightGroupInfo() {
        List<WeightGroupDomain> weightGroupList = new ArrayList<>();
        WeightGroupDomain weightGroup = new WeightGroupDomain();
        weightGroup.setId("1");
        weightGroup.setName("upto 50");
        weightGroup.setMinWeight(0);
        weightGroup.setMaxWeight(50);
        weightGroupList.add(weightGroup);

        weightGroup = new WeightGroupDomain();
        weightGroup.setId("2");
        weightGroup.setName("upto 100");
        weightGroup.setMinWeight(50);
        weightGroup.setMaxWeight(100);
        weightGroupList.add(weightGroup);

        weightGroup = new WeightGroupDomain();
        weightGroup.setId("3");
        weightGroup.setName("upto 150");
        weightGroup.setMinWeight(100);
        weightGroup.setMaxWeight(150);
        weightGroupList.add(weightGroup);

        weightGroup = new WeightGroupDomain();
        weightGroup.setId("4");
        weightGroup.setName("upto 200");
        weightGroup.setMinWeight(150);
        weightGroup.setMaxWeight(200);
        weightGroupList.add(weightGroup);

        return weightGroupList;
    }
}
