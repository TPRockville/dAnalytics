package org.jderive.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.List;
import java.util.Map;

/**
 * Created by Durga on 6/20/2015.
 */

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiObject(show = true, description = "Project json structure.")
public class JDeriveResponse {

    @ApiObjectField(description = "Status code returned by the server.", required = true)
    private String statusCode;
    private String message;
    private List<Country> countryList;
    private List<AgeGroup> ageGroupList;
    private List<WeightGroup> weightGroupList;
    private List<Drug> drugList;
    private List<DrugSummary> drugSummaryList;
    private List<DrugEventSpike> drugEventSpikeList;
    private List<DrugCharSummary> drugCharSummaryList;
    private List<DrugReactionSummary> drugReactionSummaryList;
    private List<DrugSummaryByMonth> drugSummaryByMonthList;
    private  List<ERSummary> eRSummary;
    private  List<DischargeSummary> dischargeSummary;
	private Map<String, List<Dimension>> dimensionResponse;
	private List<TopDrugs> topDrugs;


    protected JDeriveResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        this.message = builder.message;
        this.countryList = builder.countryList;
        this.ageGroupList = builder.ageGroupList;
        this.weightGroupList = builder.weightGroupList;
        this.drugList = builder.drugList;
        this.drugSummaryList = builder.drugSummaryList;
        this.drugEventSpikeList = builder.drugEventSpikeList;
        this.drugCharSummaryList = builder.drugCharSummaryList;
        this.drugReactionSummaryList = builder.drugReactionSummaryList;
        this.drugSummaryByMonthList = builder.drugSummaryByMonthList;
		this.eRSummary = builder.eRSummary;
		this.dischargeSummary = builder.dischargeSummary;
		this.dimensionResponse = builder.dimensionResponse;
		this.topDrugs = builder.topDrugs;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String statusCode;
        private String message;
        private List<Country> countryList;
        private List<AgeGroup> ageGroupList;
        private List<WeightGroup> weightGroupList;
        private List<Drug> drugList;
        private List<DrugSummary> drugSummaryList;
        private List<DrugEventSpike> drugEventSpikeList;
        private List<DrugCharSummary> drugCharSummaryList;
        private List<DrugReactionSummary> drugReactionSummaryList;
        private List<DrugSummaryByMonth> drugSummaryByMonthList;
        private List<ERSummary> eRSummary;
        private List<DischargeSummary> dischargeSummary;
    	private Map<String, List<Dimension>> dimensionResponse;
    	private List<TopDrugs> topDrugs;

        public JDeriveResponse build() {
            return new JDeriveResponse(this);
        }

        public Builder withStatusCode(String input) {
            this.statusCode = input;
            return this;
        }

        public Builder withMessage(String input) {
            this.message = input;
            return this;
        }

        public Builder withCountryList(List<Country> input) {
            this.countryList = input;
            return this;
        }

        public Builder withAgeGroupList(List<AgeGroup> input) {
            this.ageGroupList = input;
            return this;
        }

        public Builder withWeightGroupList(List<WeightGroup> input) {
            this.weightGroupList = input;
            return this;
        }

        public Builder withDrugList(List<Drug> input) {
            this.drugList = input;
            return this;
        }

        public Builder withDrugSummaryList(List<DrugSummary> input) {
            this.drugSummaryList = input;
            return this;
        }

        public Builder withDrugEventSpikeList(List<DrugEventSpike> input) {
            this.drugEventSpikeList = input;
            return this;
        }

        public Builder withDrugCharSummaryList(List<DrugCharSummary> input) {
            this.drugCharSummaryList = input;
            return this;
        }

        public Builder withDrugReactionSummaryList(List<DrugReactionSummary> input) {
            this.drugReactionSummaryList = input;
            return this;
        }

        public Builder withDrugSummaryByMonthList(List<DrugSummaryByMonth> input) {
            this.drugSummaryByMonthList = input;
            return this;
        }
		public Builder withERSummary(List<ERSummary> input) {
            this.eRSummary = input;
            return this;
        }  
		
		public Builder withDischargeSummary(List<DischargeSummary> input) {
            this.dischargeSummary = input;
            return this;
        }  
		
		public Builder withDimensionResponse(Map<String, List<Dimension>> input) {
            this.dimensionResponse = input;
            return this;
        }  
		
		public Builder withTopDrugs(List<TopDrugs> input) {
            this.topDrugs = input;
            return this;
        }  
    }
}
