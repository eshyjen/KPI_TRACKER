package com.ericsson.ipm.v1.dao;



import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.domain.KPIRoleAssignment;
import com.ericsson.ipm.v1.domain.Role;


@Repository("kpiDAO")
@Transactional
public class KPIDAOImpl extends BaseDAO<Integer, KPI> implements KPIDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(KPIDAOImpl.class);
	
	public List<KPIRoleAssignment> getAllKPIWithRoleDetails(){
		//Query query = entityManager.createQuery("from KPIRoleAssignment model");
		TypedQuery<KPIRoleAssignment> query = entityManager.createQuery("from KPIRoleAssignment model", KPIRoleAssignment.class);
		List<KPIRoleAssignment> assignmentets = query.getResultList();
		for(KPIRoleAssignment roleAssignment : assignmentets){
			KPI kpi = roleAssignment.getKpi();
			kpi.getKpiValue();
			Role role =  roleAssignment.getRole();
			role.getCode();
		}
		LOGGER.debug(" assignmentets "+ assignmentets);
		LOGGER.debug(" assignmentets.size() "+ assignmentets.size());
		return assignmentets;
	}
	
	public List<KPIRoleAssignment> getKPIWithRoleDetails(KPI kpi){
		
		Query query = entityManager.createQuery("from KPIRoleAssignment model where model.kpi=:kpi");
		query.setParameter("kpi", kpi);
		List<KPIRoleAssignment> assignmentets = query.getResultList();
		for(KPIRoleAssignment roleAssignment : assignmentets){
			Role role =  roleAssignment.getRole();
			role.getCode();
		}
		return assignmentets;
	}
	
}
