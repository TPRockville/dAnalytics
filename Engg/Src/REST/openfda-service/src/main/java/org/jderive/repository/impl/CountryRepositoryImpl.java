package org.jderive.repository.impl;

import org.hibernate.SessionFactory;
import org.jderive.domain.CountryDomain;
import org.jderive.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Repository
public class CountryRepositoryImpl implements CountryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CountryDomain> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM CountryDomain").list();
    }

    @Override
    public CountryDomain findById(String id) {
        return (CountryDomain) sessionFactory.getCurrentSession().get(CountryDomain.class, id);
    }
}
