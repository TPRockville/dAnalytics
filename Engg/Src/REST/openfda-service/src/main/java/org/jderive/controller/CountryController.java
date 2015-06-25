package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.CountryDomain;
import org.jderive.dto.CountryDTO;
import org.jderive.exception.JDeriveException;
import org.jderive.service.CountryService;
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
@RequestMapping(value = "/country", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Value("${dAnalytics.exception.message}")
    private String EXCEPTION_MESSAGE;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) throws Exception {
        try {
            CountryDomain countryDomain = countryService.findById(NumberUtil.isNumeric(id) ? NumberUtil.parseLong(id)
                    : null);
            if (countryDomain != null) {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .withCountryList(ImmutableList.of(CountryDTO.country(countryDomain)))
                        .build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            } else {
                JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                        .withStatusCode(HttpStatus.OK.toString())
                        .build();
                return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() throws Exception {
        try {
            List<CountryDomain> countryDomainList = countryService.findAll();
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withCountryList(countryDomainList.stream()
                            .map(countryDomain -> CountryDTO.country(countryDomain))
                            .collect(Collectors.toList()))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new JDeriveException(EXCEPTION_MESSAGE, e);
        }
    }
}
