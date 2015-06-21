package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.WeightGroupDomain;
import org.jderive.dto.WeightGroupDTO;
import org.jderive.service.WeightGroupService;
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
@RequestMapping(value = "/weightgroup", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeightGroupController {

    @Autowired
    private WeightGroupService weightGroupService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) {
        WeightGroupDomain weightGroupDomain = weightGroupService.findById(id);
        if (weightGroupDomain != null) {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withWeightGroupList(ImmutableList.of(WeightGroupDTO.weightGroup(weightGroupDomain))).build(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString()).build(), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() {
        List<WeightGroupDomain> weightGroupDomains = weightGroupService.findAll();
        return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                .withStatusCode(HttpStatus.OK.toString())
                .withWeightGroupList(weightGroupDomains.stream()
                        .map(weightGroupDomain -> WeightGroupDTO.weightGroup(weightGroupDomain))
                        .collect(Collectors.toList())).build(),
                HttpStatus.OK);
    }
}