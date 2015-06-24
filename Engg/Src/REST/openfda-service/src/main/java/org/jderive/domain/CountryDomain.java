package org.jderive.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Durga on 6/20/2015.
 */

@Data
@Entity
@Table(name = "COUNTRY_LIST")
public class CountryDomain {

    @Id
    @Column(name = "COUNTRY_ID")
    private Long id;

    @Column(name = "COUNTRY_NAME")
    private String name;

    @Column(name = "COUNTRY_CODE")
    private String code;
}
