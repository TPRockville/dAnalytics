package org.jderive.repository.impl;

import org.jderive.domain.CountryDomain;
import org.jderive.repository.CountryRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Repository
public class CountryRepositoryImpl implements CountryRepository {

    @Override
    public List<CountryDomain> findAll() {
        return populateCountryList();
    }

    @Override
    public CountryDomain findById(String id) {
        return populateCountryList().stream()
                .filter(countryDomain -> countryDomain.getId().equalsIgnoreCase(id))
                .findFirst().get();
    }

    private List<CountryDomain> populateCountryList() {
        List<CountryDomain> countryList = new ArrayList<>();
        CountryDomain country = new CountryDomain();
        country.setId("1");
        country.setCode("IN");
        country.setName("India");
        countryList.add(country);
        country = new CountryDomain();
        country.setId("2");
        country.setCode("US");
        country.setName("USA");
        countryList.add(country);
        country = new CountryDomain();
        country.setId("3");
        country.setCode("UK");
        country.setName("England");
        countryList.add(country);
        return countryList;
    }
}
