package org.jderive.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ER_SUMMARY")
public class ERSummaryDomain {

	@Id
	@Column(name = "ER_SUMMARY_ID")
	@Setter
	@Getter
	private int id;

	@Column(name = "YEAR")
	@Setter
	@Getter
	String year;
	
	@Column(name = "EPISODE_DISEASE_CATEGORY")
	@Setter
	@Getter
	String episodeDiseaseCategory;
	
	@Column(name = "SUM_TOTAL_ER_VISITS")
	@Setter
	@Getter
	Long sumTotalERVisits;

}
