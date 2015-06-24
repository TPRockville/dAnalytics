package org.jderive.repository.impl;

import org.hibernate.SessionFactory;
import org.jderive.domain.WeightGroupDomain;
import org.jderive.repository.WeightGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Durga on 6/20/2015.
 */
@Repository
public class WeightGroupRepositoryImpl implements WeightGroupRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<WeightGroupDomain> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM WeightGroupDomain").list();
    }

    @Override
    public WeightGroupDomain findById(Long id) {
        return (WeightGroupDomain) sessionFactory.getCurrentSession().get(WeightGroupDomain.class, id);
    }
}
