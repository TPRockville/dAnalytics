package org.jderive.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DISCHARGE_SUMMARY")
public class DischargeSummaryDomain {

	@Id
	@Column(name = "DISCHARGE_SUMMARY_ID")
	@Setter
	@Getter
	private int id;

	@Column(name = "DISCHARGE_YEAR")
	@Setter
	@Getter
	private String year;
	
	@Column(name = "APR_DRG_DESCRIPTION")
	@Setter
	@Getter
	private String aPRDRGDescription;
	
	@Column(name = "TOTAL_COUNT")
	@Setter
	@Getter
	private Long totalCount;


}
