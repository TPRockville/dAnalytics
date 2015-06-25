package org.jderive.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.jderive.domain.DischargeSummaryDomain;
import org.jderive.domain.DrugCharSummaryDomain;
import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugEventSpikeDomain;
import org.jderive.domain.DrugIndicationDomain;
import org.jderive.domain.DrugMonthSummaryDomain;
import org.jderive.domain.DrugReactionSummaryDomain;
import org.jderive.domain.ERSummaryDomain;
import org.jderive.domain.DrugSummaryDomain;
import org.jderive.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
@Repository
public class DrugRepositoryImpl implements DrugRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<DrugDomain> findAll() {
        return sessionFactory.getCurrentSession().createQuery("FROM DrugDomain").list();
    }

    @Override
    public DrugDomain findById(Long id) {
        return (DrugDomain) sessionFactory.getCurrentSession().get(DrugDomain.class, id);
    }

    @Override
    public List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummaryDomain) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DrugSummaryDomain.class, "dsm");
        if (!StringUtils.isEmpty(drugSummaryDomain.getDrugId())) {
            criteria.add(Restrictions.eq("dsm.drugId", drugSummaryDomain.getDrugId()));
        }
        if (!StringUtils.isEmpty(drugSummaryDomain.getAgeGroupId())) {
            criteria.add(Restrictions.eq("dsm.ageGroupId", drugSummaryDomain.getAgeGroupId()));
        }
        if (!StringUtils.isEmpty(drugSummaryDomain.getWeightGroupId())) {
            criteria.add(Restrictions.eq("dsm.weightGroupId", drugSummaryDomain.getWeightGroupId()));
        }
        if (!StringUtils.isEmpty(drugSummaryDomain.getCountryId())) {
            criteria.add(Restrictions.eq("dsm.countryId", drugSummaryDomain.getCountryId()));
        }
        if (!StringUtils.isEmpty(drugSummaryDomain.getStartDate())) {
            if (!StringUtils.isEmpty(drugSummaryDomain.getEndDate())) {
                criteria.add(Restrictions.between("dsm.startDate",
                        drugSummaryDomain.getStartDate(),
                        drugSummaryDomain.getEndDate()));
            } else {
                criteria.add(Restrictions.between("dsm.startDate",
                        drugSummaryDomain.getStartDate(),
                        new Date(System.currentTimeMillis())));
            }
        }

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.sum("dsm.eventCount").as("eventCount"))
					.add(Projections.groupProperty("dsm.startDate").as(
							"startDate")));
			criteria.setResultTransformer(Transformers
					.aliasToBean(DrugSummaryDomain.class));
		return criteria.list();
    }

    @Override
    public List<DrugDomain> findByName(String name) {
        String queryByName = "SELECT * FROM drug_list WHERE drug_name LIKE '" + name + "%' LIMIT 0, 100";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(queryByName);
        query.addEntity(DrugDomain.class);
        return query.list();
    }

    @Override
    public List<DrugEventSpikeDomain> eventSpikeCount(Long drugId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("From DrugEventSpikeDomain desd where desd.drugId = :drugId");
        query.setLong("drugId", drugId);
        return query.list();
    }

    @Override
    public List<DrugCharSummaryDomain> characterSummary(Long drugId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("From DrugCharSummaryDomain dcsd where dcsd.drugId = :drugId");
        query.setLong("drugId", drugId);
        return query.list();
    }

    @Override
    public List<DrugReactionSummaryDomain> reactionSummary(Long drugId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("From DrugReactionSummaryDomain drsd where drsd.drugId = :drugId " +
                        "order by drsd.eventCount desc");
        query.setLong("drugId", drugId);
        return query.list();
    }

    @Override
    public List<DrugMonthSummaryDomain> summaryMonth(
            DrugMonthSummaryDomain drugMonthSummaryDomain, boolean applyProjection) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DrugMonthSummaryDomain.class, "dmsm");
        if (!StringUtils.isEmpty(drugMonthSummaryDomain.getDrugId())) {
            criteria.add(Restrictions.eq("dmsm.drugId", drugMonthSummaryDomain.getDrugId()));
        }
        if (!StringUtils.isEmpty(drugMonthSummaryDomain.getAgeGroupId())) {
            criteria.add(Restrictions.eq("dmsm.ageGroupId", drugMonthSummaryDomain.getAgeGroupId()));
        }
        if (!StringUtils.isEmpty(drugMonthSummaryDomain.getWeightGroupId())) {
            criteria.add(Restrictions.eq("dmsm.weightGroupId", drugMonthSummaryDomain.getWeightGroupId()));
        }
        if (!StringUtils.isEmpty(drugMonthSummaryDomain.getCountryId())) {
            criteria.add(Restrictions.eq("dmsm.countryId", drugMonthSummaryDomain.getCountryId()));
        }
        if (!StringUtils.isEmpty(drugMonthSummaryDomain.getStartDate())) {
            if (!StringUtils.isEmpty(drugMonthSummaryDomain.getEndDate())) {
                criteria.add(Restrictions.between("dmsm.startDate",
                        drugMonthSummaryDomain.getStartDate(),
                        drugMonthSummaryDomain.getEndDate()));
            } else {
                criteria.add(Restrictions.between("dmsm.startDate",
                        drugMonthSummaryDomain.getStartDate(),
                        new Date(System.currentTimeMillis())));
            }
        }

        if (applyProjection) {
			criteria.setProjection(Projections
					.projectionList()
					.add(Projections.sum("dmsm.eventCount").as("eventCount"))
					.add(Projections.groupProperty("dmsm.startDate").as(
							"startDate")));
			criteria.setResultTransformer(Transformers
					.aliasToBean(DrugMonthSummaryDomain.class));
		}
		return criteria.list();
    }
	
	@Override
	public List<ERSummaryDomain> getERSummary(String drugId) {
		
		List<String> drugIndictions = getDrugIndication(drugId);
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ERSummaryDomain.class, "ersummary");
		criteria.add(Restrictions.in("ersummary.episodeDiseaseCategory",drugIndictions));
		
		return criteria.list();
	}

	@Override
	public List<DischargeSummaryDomain> getDischargeSummary(String drugId) {
		
		List<String> drugIndictions = getDrugIndication(drugId);

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DischargeSummaryDomain.class);
		
		criteria.add(Restrictions.in("aPRDRGDescription",drugIndictions));

		return criteria.list();
	}
	
	
	private List<String> getDrugIndication(String drugId) {
		String queryByName = "select drug_indication_code from drug_indication where drug_indication_id in (select drug_indication_id from drug_indication_link where drug_id ="
				+ drugId + ")";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				queryByName);
		List<String> drugIndictions = (List<String>) query .list();
		return drugIndictions;
	}
}
