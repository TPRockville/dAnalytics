package org.jderive.repository.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.jderive.domain.*;
import org.jderive.dto.DrugReactionSummaryDTO;
import org.jderive.exception.JDeriveException;
import org.jderive.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummaryDomain) throws JDeriveException {
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

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2004-01-02");
            if (drugSummaryDomain.getStartDate() == null || drugSummaryDomain.getStartDate().before(date)) {

                drugSummaryDomain.setStartDate(date);
            }
        } catch (ParseException e) {
            throw new JDeriveException("Problem with parsing the date", e);
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
        String queryByName = "SELECT * FROM drug_list WHERE drug_name LIKE \"" + name + "%\" ORDER BY drug_name LIMIT 0, 100";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(queryByName);
        query.addEntity(DrugDomain.class);
        return query.list();
    }
    
    @Override
	public DrugDomain findByExactName(String drugName) {
        String queryByName = "SELECT * FROM drug_list WHERE drug_name = '" + drugName + "'";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(queryByName);
        query.addEntity(DrugDomain.class);
        List<DrugDomain> drugDomains = query.list();
        DrugDomain drugDomain = null;
        if(drugDomains != null && !drugDomains.isEmpty())
        {
        	drugDomain = (DrugDomain)query.list().get(0);
        }
        return drugDomain;
    }

    @Override
    public List<DrugEventSpikeDomain> eventSpikeCount(Long drugId) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("From DrugEventSpikeDomain desd where desd.drugId = :drugId ORDER BY eventCount desc");
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
    public List<DrugReactionSummaryDTO> reactionSummary(Long drugId, int firstResult, int maxResults) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DrugReactionSummaryDomain.class, "drsd");
        criteria.createAlias("drsd.reactionDomain", "rd");
        criteria.add(Restrictions.eq("drsd.drugId", drugId));

        criteria.setProjection(Projections
                        .projectionList()
                        .add(Projections.property("drsd.id"), "id")
                        .add(Projections.property("drsd.drugId"), "drugId")
                        .add(Projections.property("rd.code"), "reactionName")
                        .add(Projections.property("drsd.eventCount"), "eventCount")
        );
        criteria.addOrder(Order.desc("drsd.eventCount"));
        criteria.setResultTransformer(Transformers.aliasToBean(DrugReactionSummaryDTO.class)).setFirstResult(firstResult).setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public List<DrugMonthSummaryDomain> summaryMonth(
            DrugMonthSummaryDomain drugMonthSummaryDomain, boolean applyProjection) throws JDeriveException {
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
        if (!StringUtils.isEmpty(drugMonthSummaryDomain.getGenderId())) {
            criteria.add(Restrictions.eq("dmsm.genderId", drugMonthSummaryDomain.getGenderId()));
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2004-01-02");
            if (drugMonthSummaryDomain.getStartDate() == null || drugMonthSummaryDomain.getStartDate().before(date)) {

                drugMonthSummaryDomain.setStartDate(date);
            }
        } catch (ParseException e) {
            throw new JDeriveException("Problem with parsing the date", e);
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
        criteria.add(Restrictions.in("ersummary.episodeDiseaseCategory", drugIndictions));

        return criteria.list();
    }

    @Override
    public List<DischargeSummaryDomain> getDischargeSummary(String drugId) {

        List<String> drugIndictions = getDrugIndication(drugId);

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DischargeSummaryDomain.class);

        criteria.add(Restrictions.in("aPRDRGDescription", drugIndictions));

        return criteria.list();
    }

    @Override
    public List<DrugOnlyMonthSummaryDomain> drugOnlySummaryMonth(Long drugId) throws JDeriveException {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DrugOnlyMonthSummaryDomain.class, "dmsm");
        if (!StringUtils.isEmpty(drugId)) {
            criteria.add(Restrictions.eq("dmsm.drugId", drugId));
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse("2004-01-02");
            criteria.add(Restrictions.ge("dmsm.startDate", date));

            criteria.setProjection(Projections.projectionList()
                            .add(Projections.sum("dmsm.eventCount").as("eventCount"))
                            .add(Projections.groupProperty("dmsm.startDate").as("startDate"))

            );
            criteria.setResultTransformer(Transformers
                    .aliasToBean(DrugOnlyMonthSummaryDomain.class));

        } catch (ParseException e) {
            throw new JDeriveException("Problem with parsing the date", e);
        }
        criteria.addOrder(Order.asc("dmsm.startDate"));
        return criteria.list();
    }


    private List<String> getDrugIndication(String drugId) {
        String queryByName = "select drug_indication_code from drug_indication where drug_indication_id in (select drug_indication_id from drug_indication_link where drug_id ="
                + drugId + ")";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                queryByName);
        List<String> drugIndictions = (List<String>) query.list();
        return drugIndictions;
    }

	@Override
	public List<Object[]> getTopOrBottomDrugs(int drugcount, String order) {
		
		
		 String queryByName = "select sum(event_count) as ec,drug_id from drug_only_summary_month group by drug_id order by ec "+order+" limit "+ drugcount+";";
	        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
	                queryByName);
	        List<Object[]> drugByEventCount = (List<Object[]>) query.list();
	        DrugDomain drugDomain = null;
	       
	        for (Object[] objects : drugByEventCount) {
				
	        	drugDomain = findById(Long.parseLong(objects[1].toString()));
	        	objects[1] = drugDomain.getName();
			}
	        
	        
		return drugByEventCount;
	}

	
}
