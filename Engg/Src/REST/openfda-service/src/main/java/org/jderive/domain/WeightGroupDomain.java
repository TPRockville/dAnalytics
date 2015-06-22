package org.jderive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Durga on 6/20/2015.
 */
@Entity
@Table(name = "WEIGHT_GROUP_LIST")
@Setter @Getter
public class WeightGroupDomain {

    @Id
    @Column(name = "WEIGHT_GROUP_ID")
    private String id;

    @Column(name = "WEIGHT_GROUP")
    private String name;

    @Column(name = "MIN_WEIGHT")
    private int minWeight;

    @Column(name = "MAX_WEIGHT")
    private int maxWeight;

}
