package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.AgeGroupDomain;
import org.jderive.dto.AgeGroupDTO;
import org.jderive.service.AgeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping(value = "/agegroup", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgeGroupController {

    @Autowired
    private AgeGroupService ageGroupService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) {
        AgeGroupDomain ageGroupDomain = ageGroupService.findById(id);
        if (ageGroupDomain != null) {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withAgeGroupList(ImmutableList.of(AgeGroupDTO.ageGroup(ageGroupDomain))).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString()).build(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() {
        List<AgeGroupDomain> ageGroupDomains = ageGroupService.findAll();
        return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                .withStatusCode(HttpStatus.OK.toString())
                .withAgeGroupList(ageGroupDomains.stream()
                        .map(ageGroupDomain -> AgeGroupDTO.ageGroup(ageGroupDomain))
                        .collect(Collectors.toList())).build(),
                HttpStatus.OK);
    }
}
