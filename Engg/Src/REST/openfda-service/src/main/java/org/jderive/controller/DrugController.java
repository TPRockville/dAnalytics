package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.dto.DrugDTO;
import org.jderive.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Durga on 6/21/2015.
 */

@RestController
@RequestMapping(value = "/drugs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugController {

    @Autowired
    private DrugService drugService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() {
        List<DrugDomain> drugDomains = drugService.findAll();
        JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                .withStatusCode(HttpStatus.OK.toString())
                .withDrugList(drugDomains.stream()
                        .map(drugDomain -> DrugDTO.drug(drugDomain))
                        .collect(Collectors.toList()))
                .build();
        return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) {
        DrugDomain drugDomain = drugService.findById(id);
        if (drugDomain != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withDrugList(ImmutableList.of(DrugDTO.drug(drugDomain)))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/eventcount", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@RequestParam(value = "drugId", required = false) String drugId,
                                               @RequestParam(value = "countryId", required = false) String countryId,
                                               @RequestParam(value = "ageGroupId", required = false) String ageGroupId,
                                               @RequestParam(value = "weightGroupId", required = false) String weightGroupId,
                                               @RequestParam(value = "startDate", required = false) String startDate,
                                               @RequestParam(value = "endDate", required = false) String endDate) {
        DrugSummaryDomain drugSummaryDomain = new DrugSummaryDomain();
        drugSummaryDomain.setDrugId(drugId);
        drugSummaryDomain.setAgeGroupId(ageGroupId);
        drugSummaryDomain.setWeightGroupId(weightGroupId);
        drugSummaryDomain.setCountryId(countryId);
        drugSummaryDomain.setStartDate(startDate != null ? new Date(Long.parseLong(startDate)) : null);
        drugSummaryDomain.setEndDate(endDate != null ? new Date(Long.parseLong(endDate)) : null);
        List<DrugSummaryDomain> drugSummaryDomainList = drugService.summary(drugSummaryDomain);

        if (drugSummaryDomainList != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withDrugSummaryList(drugSummaryDomainList.stream()
                            .map(dbDrugSummaryDomain -> DrugDTO.drugSummary(dbDrugSummaryDomain))
                            .collect(Collectors.toList()))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.NOT_FOUND);
        }
    }

    private MultiValueMap<String, String> headers() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "*");
        return headers;
    }
}
