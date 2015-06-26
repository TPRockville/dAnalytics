package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.WeightGroupDomain;
import org.jderive.dto.WeightGroupDTO;
import org.jderive.exception.JDeriveException;
import org.jderive.service.WeightGroupService;
import org.jderive.util.CacheUtil;
import org.jderive.util.NumberUtil;
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
@RestController
@RequestMapping(value = "/weightgroup", produces = MediaType.APPLICATION_JSON_VALUE)
public class WeightGroupController {

    @Autowired
    private WeightGroupService weightGroupService;

    @Value("${dAnalytics.exception.message}")
    private String EXCEPTION_MESSAGE;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) throws Exception {
        try {
            WeightGroupDomain weightGroupDomain = weightGroupService.findById(NumberUtil.isNumeric(id) ?
                    NumberUtil.parseLong(id) : null);

            if (weightGroupDomain != null) {
                return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withWeightGroupList(ImmutableList.of(WeightGroupDTO.weightGroup(weightGroupDomain))).build(),
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
            List<WeightGroupDomain> weightGroupDomains = weightGroupService.findAll();
            CacheUtil.addWeightGroupToCache(weightGroupDomains.toArray(new WeightGroupDomain[weightGroupDomains.size()]));
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withWeightGroupList(weightGroupDomains.stream()
                            .map(weightGroupDomain -> WeightGroupDTO.weightGroup(weightGroupDomain))
                            .collect(Collectors.toList())).build(), HttpStatus.OK);
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }
}
