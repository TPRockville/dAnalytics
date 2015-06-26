package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.AgeGroupDomain;
import org.jderive.dto.AgeGroupDTO;
import org.jderive.exception.JDeriveException;
import org.jderive.service.AgeGroupService;
import org.jderive.util.CacheUtil;
import org.jderive.util.NumberUtil;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Durga on 6/20/2015.
 */
@Api(name = "AgeGroup Service.", description = "AgeGroup service to fetch the AgeGroup info.")
@RestController
@RequestMapping(value = "/agegroup", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgeGroupController {

    @Autowired
    private AgeGroupService ageGroupService;

    @Value("${dAnalytics.exception.message}")
    private String EXCEPTION_MESSAGE;

    @ApiMethod(path = "/{id}", description = "Fetch AgeGroup info by Id.", responsestatuscode = "200",
            produces = MediaType.APPLICATION_JSON_VALUE)

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiResponseObject
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) throws Exception {
        try {
            AgeGroupDomain ageGroupDomain = ageGroupService.findById(NumberUtil.isNumeric(id) ? NumberUtil.parseLong(id)
                    : null);
            if (ageGroupDomain != null) {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withAgeGroupList(ImmutableList.of(AgeGroupDTO.ageGroup(ageGroupDomain))).build(),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.OK.toString()).build(), HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() throws Exception {
        try {
            List<AgeGroupDomain> ageGroupDomains = ageGroupService.findAll();
            CacheUtil.addAgeGroupToCache(ageGroupDomains.toArray(new AgeGroupDomain[ageGroupDomains.size()]));
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withAgeGroupList(ageGroupDomains.stream()
                            .map(ageGroupDomain -> AgeGroupDTO.ageGroup(ageGroupDomain))
                            .collect(Collectors.toList())).build(), HttpStatus.OK);
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }
}
