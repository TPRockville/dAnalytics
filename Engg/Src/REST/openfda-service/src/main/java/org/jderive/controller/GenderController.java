package org.jderive.controller;

import com.google.common.collect.ImmutableList;
import org.jderive.api.Gender;
import org.jderive.api.JDeriveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@RestController
@RequestMapping(value = "/gender", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenderController {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> get(@PathVariable("id") int id) {
        Gender gender = populateGenderInfo().get(id);
        if (gender != null) {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.OK.toString())
                    .withGenderList(ImmutableList.of(gender)).build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                    .withStatusCode(HttpStatus.NOT_FOUND.toString()).build(), HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<JDeriveResponse> list() {
        return new ResponseEntity<JDeriveResponse>(JDeriveResponse.builder()
                .withStatusCode(HttpStatus.OK.toString())
                .withGenderList(populateGenderInfo()).build(), HttpStatus.OK);

    }

    private List<Gender> populateGenderInfo() {
        List<Gender> genderList = new ArrayList<>();
        Gender gender = new Gender();
        gender.setId("1");
        gender.setName("Male");
        genderList.add(gender);
        gender = new Gender();
        gender.setId("2");
        gender.setName("Female");
        genderList.add(gender);
        return genderList;
    }
}
