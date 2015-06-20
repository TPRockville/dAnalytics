package org.jderive.repository;

import org.jderive.domain.CountryDomain;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

public interface CountryRepository {

    List<CountryDomain> findAll();

    CountryDomain findById(String id);
}
