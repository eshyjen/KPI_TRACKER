package com.ericsson.ipm.v1.service;

import java.util.List;

import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.dto.KPIDTO;
import com.ericsson.ipm.v1.dto.KPIRoleDTO;

public interface KPIService {

	
	public KPI save(KPI entity);
	
	public void remove(KPI entity);

	public KPI update(KPI entity);

	public KPI findById(Integer id);
	
	public List<KPI> findAll();
	
	public List<KPIRoleDTO> getKPIRoleDetails();
	
	public KPIDTO getKPIDetails(String kpiId);
	
}
