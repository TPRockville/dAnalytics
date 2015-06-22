package org.jderive.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JDeriveResponse {

    private final String statusCode;
    private final List<Country> countryList;
    private final List<Gender> genderList;
    private final List<AgeGroup> ageGroupList;
    private final List<WeightGroup> weightGroupList;
    private final List<EventSeverity> eventSeverityList;
    private final List<Drug> drugList;
    private final List<DrugSummary> drugSummaryList;

    protected JDeriveResponse(Builder builder) {
        this.statusCode = builder.statusCode;
        this.countryList = builder.countryList;
        this.genderList = builder.genderList;
        this.ageGroupList = builder.ageGroupList;
        this.weightGroupList = builder.weightGroupList;
        this.eventSeverityList = builder.eventSeverityList;
        this.drugList = builder.drugList;
        this.drugSummaryList = builder.drugSummaryList;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String statusCode;
        private List<Country> countryList;
        private List<Gender> genderList;
        private List<AgeGroup> ageGroupList;
        private List<WeightGroup> weightGroupList;
        private List<EventSeverity> eventSeverityList;
        private List<Drug> drugList;
        private List<DrugSummary> drugSummaryList;

        public JDeriveResponse build() {
            return new JDeriveResponse(this);
        }

        public Builder withStatusCode(String input) {
            this.statusCode = input;
            return this;
        }

        public Builder withCountryList(List<Country> input) {
            this.countryList = input;
            return this;
        }

        public Builder withGenderList(List<Gender> input) {
            this.genderList = input;
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

        public Builder withEventSeverityList(List<EventSeverity> input) {
            this.eventSeverityList = input;
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
    }
}
