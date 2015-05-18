package com.ericsson.ipm.v1.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.domain.KPIRoleAssignment;
import com.ericsson.ipm.v1.domain.Role;

@Repository("roleDAO")
@Transactional
public class RoleDAOImpl extends BaseDAO<Integer, Role> implements RoleDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleDAOImpl.class);

	public static final String CODE = "code";
	
	private EntityManager getEntityManager() {
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Role> findByProperty(String propertyName, final Object value) {
		LOGGER.info("finding Role instance with property: "
				+ propertyName + ", value: " + value);
		try {
			final String queryString = "select model from Role model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.info("find by property name failed"+ re);
			throw re;
		}
	}


	@Override
	public List<Role> findByCode(String code) {
		return findByProperty(CODE, code);
	}
	
	public List<Role> getAllRoleWithKPIDetails(){
		TypedQuery<Role> query = entityManager.createQuery("from KPIRoleAssignment model", Role.class);
		List<Role> roles = query.getResultList();
		for(Role role : roles){
		for(KPIRoleAssignment roleAssignment : role.getKpiRoleAssignments()){
			KPI kpi = roleAssignment.getKpi();
		}
	  }
		return roles;
	}
	
}
