package org.jderive.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Durga on 6/21/2015.
 */
@Data
@Entity
@Table(name = "DRUG_LIST")
public class DrugDomain {

    @Id
    @Column(name = "DRUG_ID")
    private Long id;

    @Column(name = "DRUG_NAME")
    private String name;
}
