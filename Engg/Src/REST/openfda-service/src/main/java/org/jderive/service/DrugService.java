package org.jderive.service;

import org.jderive.domain.DrugDomain;
import org.jderive.domain.DrugSummaryDomain;

import java.util.List;

/**
 * Created by Durga on 6/21/2015.
 */
public interface DrugService {

    List<DrugDomain> findAll();

    DrugDomain findById(String id);

    List<DrugSummaryDomain> summary(DrugSummaryDomain drugSummary);
}
