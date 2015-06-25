package org.jderive.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "DRUG_INDICATION")
public class DrugIndicationDomain {
	
	 @Id
	    @Column(name = "DRUG_INDICATION_ID")
	    @Setter
	    @Getter
	    private Long id;
	    
	    @Column(name = "DRUG_INDICATION_CODE")
	    @Setter
	    @Getter
	    private String drugIndicationCode;


}
