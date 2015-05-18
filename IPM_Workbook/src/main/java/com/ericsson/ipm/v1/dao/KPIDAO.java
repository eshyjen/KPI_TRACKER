package com.ericsson.ipm.v1.dao;

import java.util.List;

import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.domain.KPIRoleAssignment;

public interface KPIDAO {

	
	public KPI save(KPI entity);
	
	public void remove(KPI entity);

	public KPI update(KPI entity);

	public KPI findById(Integer id);
	
	public List<KPI> findAll();
	
	public List<KPIRoleAssignment> getAllKPIWithRoleDetails();
	
	public List<KPIRoleAssignment> getKPIWithRoleDetails(KPI kpi);
	
}
