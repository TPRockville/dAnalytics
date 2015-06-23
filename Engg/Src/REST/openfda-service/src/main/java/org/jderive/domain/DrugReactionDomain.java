package org.jderive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Durga on 6/23/2015.
 */
@Entity
@Table(name = "REACTION_LIST")
@Setter @Getter
public class DrugReactionDomain {

    @Id
    @Column(name = "REACTION_ID")
    private Long id;

    @Column(name = "REACTION_CODE")
    private String code;
}
