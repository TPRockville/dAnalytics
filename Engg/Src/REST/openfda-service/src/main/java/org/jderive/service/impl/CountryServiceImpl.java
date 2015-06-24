package org.jderive.service.impl;

import org.jderive.domain.CountryDomain;
import org.jderive.repository.CountryRepository;
import org.jderive.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CountryDomain> findAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CountryDomain findById(Long id) {
        return countryRepository.findById(id);
    }
}
