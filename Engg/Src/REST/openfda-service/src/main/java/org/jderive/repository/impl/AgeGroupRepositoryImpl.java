package org.jderive.repository.impl;

import org.hibernate.SessionFactory;
import org.jderive.domain.AgeGroupDomain;
import org.jderive.repository.AgeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */

@Repository
public class AgeGroupRepositoryImpl implements AgeGroupRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AgeGroupDomain> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM AgeGroupDomain").list();
    }

    @Override
    public AgeGroupDomain findById(String id) {
        return (AgeGroupDomain) sessionFactory.getCurrentSession().get(AgeGroupDomain.class, id);
    }
}
