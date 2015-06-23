package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.apache.commons.collections.CollectionUtils;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.dto.DrugDTO;
import org.jderive.service.DrugService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@Api(name = "Drugs Service", description = "Drugs service to fetch the various drug information.")
@RestController
@RequestMapping(value = "/drugs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrugController {

    @Autowired
    private DrugService drugService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiMethod(path = "/list", description = "Grug List service, returns all the Drugs.",
            produces = MediaType.APPLICATION_JSON_VALUE, responsestatuscode = "200")
    public ResponseEntity<JDeriveResponse> list() {
        List<DrugDomain> drugDomains = drugService.findAll();
        JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                .withStatusCode(HttpStatus.OK.toString())
                .withDrugList(drugDomains.stream()
                        .map(drugDomain -> DrugDTO.drug(drugDomain))
                        .collect(Collectors.toList()))
                .build();
        return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) {
        DrugDomain drugDomain = drugService.findById(id);
        if (drugDomain != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withDrugList(ImmutableList.of(DrugDTO.drug(drugDomain)))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> autoComplete(@PathVariable("name") String name) {
        List<DrugDomain> drugDomainList = drugService.findByName(name);
        if (drugDomainList != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withDrugList(drugDomainList.stream()
                            .map(dbDrugSummaryDomain -> DrugDTO.drug(dbDrugSummaryDomain))
                            .collect(Collectors.toList()))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/eventcount", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@RequestParam(value = "drugId", required = false) String drugId,
                                               @RequestParam(value = "countryId", required = false) String countryId,
                                               @RequestParam(value = "ageGroupId", required = false) String ageGroupId,
                                               @RequestParam(value = "weightGroupId", required = false)
                                               String weightGroupId,
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
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{drugId}/spike", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> spike(@PathVariable("drugId") String drugId) {
        try {
            List<DrugEventSpikeDomain> drugEventSpikeDomainList = drugService.eventSpikeCount(drugId);
            if (CollectionUtils.isNotEmpty(drugEventSpikeDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder().withStatusCode(HttpStatus.OK.toString())
                        .withDrugEventSpikeList(drugEventSpikeDomainList.stream()
                                .map(drugEventSpikeDomain -> DrugDTO.drugEventSpike(drugEventSpikeDomain))
                                .collect(Collectors.toList())).build();

                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString())
                        .withDrugEventSpikeList(ImmutableList.of()).build(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode("500").withMessage(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{drugId}/characterization", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> characterization(@PathVariable("drugId") String drugId) {
        try {
            List<DrugCharSummaryDomain> drugCharSummaryDomainList = drugService.characterSummary(drugId);
            if (CollectionUtils.isNotEmpty(drugCharSummaryDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder().withStatusCode(HttpStatus.OK.toString())
                        .withDrugCharSummaryList(drugCharSummaryDomainList.stream()
                                .map(drugCharSummaryDomain -> DrugDTO.drugCharSummary(drugCharSummaryDomain))
                                .collect(Collectors.toList())).build();

                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString())
                        .withDrugCharSummaryList(ImmutableList.of())
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode("500").withMessage(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{drugId}/reaction", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> reactionSummary(@PathVariable("drugId") String drugId) {
        try {
            List<DrugReactionSummaryDomain> drugReactionSummaryDomainList = drugService.reactionSummary(drugId);
            if (CollectionUtils.isNotEmpty(drugReactionSummaryDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder().withStatusCode(HttpStatus.OK.toString())
                        .withDrugReactionSummaryList(drugReactionSummaryDomainList.stream()
                                .map(drugReactionSummaryDomain -> DrugDTO.drugReactionSummary(drugReactionSummaryDomain))
                                .collect(Collectors.toList())).build();

                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString())
                        .withDrugReactionSummaryList(ImmutableList.of())
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode("500").withMessage(e.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eventcount/month", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> getSummaryMonth(@RequestParam(value = "drugId", required = false)
                                                               String drugId,
                                               @RequestParam(value = "countryId", required = false) String countryId,
                                               @RequestParam(value = "ageGroupId", required = false) String ageGroupId,
                                               @RequestParam(value = "weightGroupId", required = false)
                                               String weightGroupId,
                                               @RequestParam(value = "startDate", required = false) String startDate,
                                               @RequestParam(value = "endDate", required = false) String endDate) {
        DrugMonthSummaryDomain drugMonthSummaryDomain = new DrugMonthSummaryDomain();
        drugMonthSummaryDomain.setDrugId(drugId);
        drugMonthSummaryDomain.setAgeGroupId(ageGroupId);
        drugMonthSummaryDomain.setWeightGroupId(weightGroupId);
        drugMonthSummaryDomain.setCountryId(countryId);
        drugMonthSummaryDomain.setStartDate(startDate != null ? new Date(Long.parseLong(startDate)) : null);
        drugMonthSummaryDomain.setEndDate(endDate != null ? new Date(Long.parseLong(endDate)) : null);
        List<DrugMonthSummaryDomain> drugMonthSummaryDomainList = drugService.summaryMonth(drugMonthSummaryDomain);

        if (drugMonthSummaryDomainList != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withDrugSummaryByMonthList(drugMonthSummaryDomainList.stream()
                            .map(dbDrugMonthSummaryDomain -> DrugDTO.drugMonthSummary(dbDrugMonthSummaryDomain))
                            .collect(Collectors.toList()))
                    .build();

            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .withDrugSummaryByMonthList(ImmutableList.of()).build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
        }
    }
}
