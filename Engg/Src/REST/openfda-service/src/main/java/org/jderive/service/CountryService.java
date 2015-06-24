package org.jderive.service;

import org.jderive.domain.CountryDomain;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Service
public interface CountryService {

    List<CountryDomain> findAll();

    CountryDomain findById(Long id);
}
