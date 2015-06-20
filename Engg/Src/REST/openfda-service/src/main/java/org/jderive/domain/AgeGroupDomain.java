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
@Table(name = "AGE_GROUP")
@Setter @Getter
public class AgeGroupDomain {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MIN_AGE")
    private int minAge;

    @Column(name = "MAX_AGE")
    private int maxAge;
}
