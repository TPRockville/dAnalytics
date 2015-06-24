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
@Table(name = "AGE_GROUP_LIST")
@Setter @Getter
public class AgeGroupDomain {

    @Id
    @Column(name = "AGE_GROUP_ID")
    private Long id;

    @Column(name = "AGE_GROUP")
    private String name;
}
