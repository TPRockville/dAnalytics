package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.JDeriveResponse;
import org.jderive.domain.CountryDomain;
import org.jderive.dto.CountryDTO;
import org.jderive.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") String id) {
        CountryDomain countryDomain = countryService.findById(id);
        if (countryDomain != null) {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withCountryList(ImmutableList.of(CountryDTO.country(countryDomain)))
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.OK);
        } else {
            JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString())
                    .build();
            return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() {
        List<CountryDomain> countryDomainList = countryService.findAll();
        JDeriveResponse jDeriveResponse = JDeriveResponse.builder()
                .withStatusCode(HttpStatus.OK.toString())
                .withCountryList(countryDomainList.stream()
                        .map(countryDomain -> CountryDTO.country(countryDomain))
                        .collect(Collectors.toList()))
                .build();
        return new ResponseEntity<JDeriveResponse>(jDeriveResponse, headers(), HttpStatus.OK);
    }

    private MultiValueMap<String, String> headers() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Origin", "*");
        return headers;
    }
}
