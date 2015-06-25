package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jderive.api.Dimension;
import org.jderive.api.DimensionResponse;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.DischargeSummaryDomain;
import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.dto.DimensionDTO;
import org.jderive.domain.ERSummaryDomain;
import org.jderive.dto.DrugDTO;
import org.jderive.exception.JDeriveException;
import org.jderive.dto.ERSummaryDTO;
import org.jderive.dto.DischargeSummaryDTO;
import org.jderive.service.DrugService;
import org.jderive.util.NumberUtil;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

/**
 * Created by Durga on 6/21/2015.
 */
@Api(name = "Drugs Service", description = "Drugs service to fetch the various drug information.")
@RestController
@RequestMapping(value = "/drugs", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class DrugController {

    @Autowired
    private DrugService drugService;

    @Value("${dAnalytics.exception.message}")
    private String EXCEPTION_MESSAGE;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiMethod(path = "/list", description = "Grug List service, returns all the Drugs.", produces = MediaType.APPLICATION_JSON_VALUE, responsestatuscode = "200")
    public ResponseEntity<JDeriveResponse> list() throws Exception {
        try {

            List<DrugDomain> drugDomains = drugService.findAll();
            JDeriveResponse jDeriveResponse = JDeriveResponse
                    .builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withDrugList(
                            drugDomains.stream().map(drugDomain -> DrugDTO.drug(drugDomain))
                                    .collect(Collectors.toList())).build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) throws Exception {
        try {
            DrugDomain drugDomain = drugService.findById(NumberUtil.isNumeric(id) ? NumberUtil.parseLong(id) : null);
            if (drugDomain != null) {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder().withStatusCode(HttpStatus.OK.toString())
                        .withDrugList(ImmutableList.of(DrugDTO.drug(drugDomain))).build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString()).build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> autoComplete(@PathVariable("name") String name) throws Exception {
        try {
            List<DrugDomain> drugDomainList = drugService.findByName(name);
            if (CollectionUtils.isNotEmpty(drugDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse
                        .builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withDrugList(
                                drugDomainList.stream().map(dbDrugSummaryDomain -> DrugDTO.drug(dbDrugSummaryDomain))
                                        .collect(Collectors.toList())).build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString()).build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/eventcount", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@RequestParam(value = "drugId", required = false) String drugId,
            @RequestParam(value = "countryId", required = false) String countryId,
            @RequestParam(value = "ageGroupId", required = false) String ageGroupId,
            @RequestParam(value = "weightGroupId", required = false) String weightGroupId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) throws Exception {
        try {

            List<DrugSummaryDomain> drugSummaryDomainList = drugService.summary(drugSummaryDomain(drugId, countryId,
                    ageGroupId, weightGroupId, startDate, endDate));

            if (drugSummaryDomainList != null) {
                JDeriveResponse jDeriveResponse = JDeriveResponse
                        .builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withDrugSummaryList(
                                drugSummaryDomainList.stream()
                                        .map(dbDrugSummaryDomain -> DrugDTO.drugSummary(dbDrugSummaryDomain))
                                        .collect(Collectors.toList())).build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString()).build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/{drugId}/spike", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> spike(@PathVariable("drugId") String drugId) throws Exception {
        try {
            List<DrugEventSpikeDomain> drugEventSpikeDomainList = drugService.eventSpikeCount(NumberUtil
                    .isNumeric(drugId) ? NumberUtil.parseLong(drugId) : null);
            if (CollectionUtils.isNotEmpty(drugEventSpikeDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse
                        .builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withDrugEventSpikeList(
                                drugEventSpikeDomainList.stream()
                                        .map(drugEventSpikeDomain -> DrugDTO.drugEventSpike(drugEventSpikeDomain))
                                        .collect(Collectors.toList())).build();

                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString()).withDrugEventSpikeList(ImmutableList.of())
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/{drugId}/characterization", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> characterization(@PathVariable("drugId") String drugId) throws Exception {
        try {
            List<DrugCharSummaryDomain> drugCharSummaryDomainList = drugService.characterSummary(NumberUtil
                    .isNumeric(drugId) ? NumberUtil.parseLong(drugId) : null);
            if (CollectionUtils.isNotEmpty(drugCharSummaryDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse
                        .builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withDrugCharSummaryList(
                                drugCharSummaryDomainList.stream()
                                        .map(drugCharSummaryDomain -> DrugDTO.drugCharSummary(drugCharSummaryDomain))
                                        .collect(Collectors.toList())).build();

                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString()).withDrugCharSummaryList(ImmutableList.of())
                        .build(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/{drugId}/reaction", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> reactionSummary(@PathVariable("drugId") String drugId) throws Exception {
        try {
            List<DrugReactionSummaryDomain> drugReactionSummaryDomainList = drugService.reactionSummary(NumberUtil
                    .isNumeric(drugId) ? NumberUtil.parseLong(drugId) : null);
            if (CollectionUtils.isNotEmpty(drugReactionSummaryDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse
                        .builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withDrugReactionSummaryList(
                                drugReactionSummaryDomainList
                                        .stream()
                                        .map(drugReactionSummaryDomain -> DrugDTO
                                                .drugReactionSummary(drugReactionSummaryDomain))
                                        .collect(Collectors.toList())).build();

                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString())
                        .withDrugReactionSummaryList(ImmutableList.of()).build(), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/eventcount/month", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> getSummaryMonth(
            @RequestParam(value = "drugId", required = false) String drugId,
            @RequestParam(value = "countryId", required = false) String countryId,
            @RequestParam(value = "ageGroupId", required = false) String ageGroupId,
            @RequestParam(value = "weightGroupId", required = false) String weightGroupId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) throws Exception {
        try {
            DrugMonthSummaryDomain drugMonthSummaryDomain = new DrugMonthSummaryDomain();
            BeanUtils.copyProperties(
                    drugSummaryDomain(drugId, countryId, ageGroupId, weightGroupId, startDate, endDate),
                    drugMonthSummaryDomain);
            List<DrugMonthSummaryDomain> drugMonthSummaryDomainList = drugService.summaryMonth(drugMonthSummaryDomain,
                    true);

            if (CollectionUtils.isNotEmpty(drugMonthSummaryDomainList)) {
                JDeriveResponse jDeriveResponse = JDeriveResponse
                        .builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withDrugSummaryByMonthList(
                                drugMonthSummaryDomainList
                                        .stream()
                                        .map(dbDrugMonthSummaryDomain -> DrugDTO
                                                .drugMonthSummary(dbDrugMonthSummaryDomain))
                                        .collect(Collectors.toList())).build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.NOT_FOUND.toString()).withDrugSummaryByMonthList(ImmutableList.of())
                        .build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/{drugId}/dimensions", method = RequestMethod.GET)
    public ResponseEntity<DimensionResponse> getDimensions(@PathVariable("drugId") String drugId,
            @RequestParam(value = "date", required = false) String date) {
        DrugMonthSummaryDomain drugMonthSummaryDomain = new DrugMonthSummaryDomain();
        drugMonthSummaryDomain.setDrugId(Long.valueOf(1132980168175695l));
        drugMonthSummaryDomain.setStartDate(new Date(Long.valueOf(1396310400l) * 1000));

        List<DrugMonthSummaryDomain> drugSummaryList = drugService.summaryMonth(drugMonthSummaryDomain, false);
        DimensionResponse dimensionResponseFromDB = DimensionDTO.transform(drugSummaryList);
        return new ResponseEntity<DimensionResponse>(dimensionResponseFromDB, HttpStatus.OK);
    }

    private DrugSummaryDomain drugSummaryDomain(String drugId, String countryId, String ageGroupId,
            String weightGroupId, String startDate, String endDate) {
        DrugSummaryDomain drugSummaryDomain = new DrugSummaryDomain();
        drugSummaryDomain.setDrugId(NumberUtil.isNumeric(drugId) ? NumberUtil.parseLong(drugId) : null);
        drugSummaryDomain.setAgeGroupId(NumberUtil.isNumeric(ageGroupId) ? NumberUtil.parseLong(ageGroupId) : null);
        drugSummaryDomain.setWeightGroupId(NumberUtil.isNumeric(weightGroupId) ? NumberUtil.parseLong(weightGroupId)
                : null);
        drugSummaryDomain.setCountryId(NumberUtil.isNumeric(countryId) ? NumberUtil.parseLong(countryId) : null);
        drugSummaryDomain.setStartDate(NumberUtil.isNumeric(startDate) ? new Date(NumberUtil.parseLong(startDate))
                : null);
        drugSummaryDomain.setEndDate(NumberUtil.isNumeric(endDate) ? new Date(NumberUtil.parseLong(endDate)) : null);
        return drugSummaryDomain;
    }
    @RequestMapping(value = "{id}/indication/ersummary", method = RequestMethod.GET) 
    public ResponseEntity<JDeriveResponse> getERSummary(@PathVariable("id") String drugId)throws Exception {
    	try
    	{
    	List<ERSummaryDomain> erSummaryDomains = drugService.getERSummary(drugId);

        if (erSummaryDomains != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withERSummary(erSummaryDomains.stream()
                            .map(erSummaryDomain -> ERSummaryDTO.erSummary(erSummaryDomain))
                            .collect(Collectors.toList()))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
        }
    }catch (Exception e) {
        throw new JDeriveException(EXCEPTION_MESSAGE, e);
    }
    }
    
    
    @RequestMapping(value = "{id}/indication/dischargesummary", method = RequestMethod.GET) 
    public ResponseEntity<JDeriveResponse> getDischargeSummary(@PathVariable("id") String drugId) throws Exception {
    	
    	try
    	{
    	List<DischargeSummaryDomain> dischargeSummaryDomains = drugService.getDischargeSummary(drugId);
    	
        if (dischargeSummaryDomains != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withDischargeSummary(dischargeSummaryDomains.stream()
                            .map(dischargeSummaryDomain -> DischargeSummaryDTO.dischargeSummary(dischargeSummaryDomain))
                            .collect(Collectors.toList()))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.NOT_FOUND);
        }
    	}catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
        
    }
}
