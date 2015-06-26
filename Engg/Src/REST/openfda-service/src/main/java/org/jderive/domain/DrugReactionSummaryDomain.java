package org.jderive.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Durga on 6/23/2015.
 */
@Entity
@Table(name = "DRUG_REACTION_SUMMARY")
@Setter @Getter
public class DrugReactionSummaryDomain {

    @Id
    @Column(name = "DRUG_REACTION_SUMMARY_ID")
    private Long id;

    @Column(name = "DRUG_ID")
    private Long drugId;

    @ManyToOne
    @JoinColumn(name = "REACTION_ID", nullable = false)
    private DrugReactionDomain reactionDomain;

    @Column(name = "EVENT_COUNT")
    private Long eventCount;
}
