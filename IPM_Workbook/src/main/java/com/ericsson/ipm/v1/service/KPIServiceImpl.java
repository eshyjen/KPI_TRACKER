package com.ericsson.ipm.v1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.ipm.v1.dao.KPIDAO;
import com.ericsson.ipm.v1.domain.KPI;
import com.ericsson.ipm.v1.domain.KPIRoleAssignment;
import com.ericsson.ipm.v1.domain.Role;
import com.ericsson.ipm.v1.dto.KPIDTO;
import com.ericsson.ipm.v1.dto.KPIRoleDTO;
import com.ericsson.v1.util.IPMUtility;

@Service("kpiService")
@Transactional
public class KPIServiceImpl implements KPIService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KPIServiceImpl.class);

	private KPIDAO kpidao;;
	
	@Override
	public KPI save(KPI entity) {
		return kpidao.save(entity);
	}

	@Override
	public void remove(KPI entity) {
		kpidao.remove(entity);
		
	}

	@Override
	public KPI update(KPI entity) {
		return kpidao.update(entity);
	}

	@Override
	public KPI findById(Integer id) {
		return kpidao.findById(id);
	}

	@Override
	public List<KPI> findAll() {
		return kpidao.findAll();
	}

	public KPIDAO getKpidao() {
		return kpidao;
	}

	@Autowired
	public void setKpidao(KPIDAO kpidao) {
		this.kpidao = kpidao;
	}
	
	public List<KPIRoleDTO> getKPIRoleDetails(){
		List<KPIRoleDTO> kpiRoleDTOs = new ArrayList<KPIRoleDTO>();
		
		Map<String, KPIRoleDTO> map = new HashMap<String, KPIRoleDTO>();
		
		List<KPIRoleAssignment> kpiRoleAssignments = kpidao.getAllKPIWithRoleDetails();
		KPIRoleDTO kpiRoleDTO = null;
		KPI kpi = null;
		Role role = null;
		for(KPIRoleAssignment roleAssignment : kpiRoleAssignments){
			if(!"USER".equalsIgnoreCase(roleAssignment.getRole().getCode())){
				kpi = roleAssignment.getKpi();
				role = roleAssignment.getRole();
				//LOGGER.debug("kpi.getId() : " + kpi.getId());
				//LOGGER.debug("kpi.getKpiValue() : " + kpi.getKpiValue());
				//LOGGER.debug("role.getCode() :: " + role.getCode());
				if(map.get(kpi.getKpiDisplayName()) == null){
					kpiRoleDTO = new KPIRoleDTO();
					kpiRoleDTO.setId(kpi.getId());
					kpiRoleDTO.setKpiDisplayName(kpi.getKpiDisplayName());
					kpiRoleDTO.setKpiName(kpi.getKpiName());
					map.put(kpi.getKpiDisplayName(), kpiRoleDTO);
				} else {
					//LOGGER.debug("else kpiRoleDTO : " + kpiRoleDTO);
					kpiRoleDTO = map.get(kpi.getKpiDisplayName());
				}
				
				/*LOGGER.debug("map : " + map);
				LOGGER.debug("role : "+role.getCode()  );
				LOGGER.debug("kpi.getKpiValue() : " + kpi.getKpiValue());
				LOGGER.debug("kpiRoleDTO : " + kpiRoleDTO);*/
				IPMUtility.setKPIValue(role.getCode(), kpi.getKpiValue(), kpiRoleDTO);
			}
		}
		
		for(String key : map.keySet()){
			KPIRoleDTO dto = map.get(key);
			String kpiDisplayName = dto.getKpiDisplayName();
			String altMSG = "<img src='../../resources/tmotheme/i/info.gif' alt="+dto.getKpiName()+ " title="+dto.getKpiName()+" ></img>";
			dto.setKpiDisplayName(kpiDisplayName +" "+altMSG);
			kpiRoleDTOs.add(dto);
		}
		
		return kpiRoleDTOs;
	}
	
	public KPIDTO getKPIDetails(String kpiId){
		Integer id = null;
		KPIRoleDTO kpiRoleDTO = null;
		KPIDTO kpidto = null;
		
		if(StringUtils.isNotBlank(kpiId)){
			id = Integer.parseInt(kpiId);
			kpidto = new KPIDTO();
		}
		
		KPI kpi = kpidao.findById(id);
		
		List<KPIRoleAssignment> kpiRoleAssignments  = kpidao.getKPIWithRoleDetails(kpi);
		
		List<Integer> userSelectedRoles = new ArrayList<Integer>();
		
		kpiRoleDTO = new KPIRoleDTO();
		kpiRoleDTO.setId(kpi.getId());
		kpiRoleDTO.setKpiDisplayName(kpi.getKpiDisplayName());
		kpiRoleDTO.setKpiName(kpi.getKpiName());
		
		kpidto.setId(kpi.getId());
		kpidto.setKpiDisplayName(kpi.getKpiDisplayName());
		kpidto.setKpiName(kpi.getKpiName());
		
		for(KPIRoleAssignment roleAssignment : kpiRoleAssignments){
			Role role = roleAssignment.getRole();
			IPMUtility.setKPIValue(role.getCode(), kpi.getKpiValue(), kpiRoleDTO);
			userSelectedRoles.add(role.getId());
		}
		
		if(userSelectedRoles.size() > 1 ){
			kpidto.setKpiVaueSameForAllRoles(Boolean.FALSE);
		}
		
		kpidto.setUserSelectedRoles(userSelectedRoles);
		kpidto.setKpiRoleDTO(kpiRoleDTO);
		
		return kpidto;
	}

}
