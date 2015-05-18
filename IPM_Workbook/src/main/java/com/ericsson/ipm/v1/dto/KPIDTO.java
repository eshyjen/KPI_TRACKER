package com.ericsson.ipm.v1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class KPIDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private int id;
	private String kpiDisplayName;
	private String kpiValue;
	private String kpiName;
	private String kpiDescription;
	private Boolean kpiVaueSameForAllRoles = Boolean.TRUE;
	private List<Integer> userSelectedRoles = new ArrayList<Integer>();
	
	private KPIRoleDTO kpiRoleDTO = new KPIRoleDTO();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKpiDisplayName() {
		return kpiDisplayName;
	}

	public void setKpiDisplayName(String kpiDisplayName) {
		this.kpiDisplayName = kpiDisplayName;
	}

	public String getKpiValue() {
		return kpiValue;
	}

	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}

	public String getKpiName() {
		return kpiName;
	}

	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}

	public String getKpiDescription() {
		return kpiDescription;
	}

	public void setKpiDescription(String kpiDescription) {
		this.kpiDescription = kpiDescription;
	}

	
	public List<Integer> getUserSelectedRoles() {
		return userSelectedRoles;
	}

	public void setUserSelectedRoles(List<Integer> userSelectedRoles) {
		this.userSelectedRoles = userSelectedRoles;
	}

	public Boolean getKpiVaueSameForAllRoles() {
		return kpiVaueSameForAllRoles;
	}

	public void setKpiVaueSameForAllRoles(Boolean kpiVaueSameForAllRoles) {
		this.kpiVaueSameForAllRoles = kpiVaueSameForAllRoles;
	}

	public KPIRoleDTO getKpiRoleDTO() {
		return kpiRoleDTO;
	}

	public void setKpiRoleDTO(KPIRoleDTO kpiRoleDTO) {
		this.kpiRoleDTO = kpiRoleDTO;
	}

	@Override
	public String toString() {
		return "KPIDTO [id=" + id + ", kpiDisplayName=" + kpiDisplayName
				+ ", kpiValue=" + kpiValue + ", kpiName=" + kpiName
				+ ", kpiDescription=" + kpiDescription
				+ ", kpiVaueSameForAllRoles=" + kpiVaueSameForAllRoles
				+ ", userSelectedRoles=" + userSelectedRoles + ", kpiRoleDTO="
				+ kpiRoleDTO + ", getId()=" + getId()
				+ ", getKpiDisplayName()=" + getKpiDisplayName()
				+ ", getKpiValue()=" + getKpiValue() + ", getKpiName()="
				+ getKpiName() + ", getKpiDescription()=" + getKpiDescription()
				+ ", getUserSelectedRoles()=" + getUserSelectedRoles()
				+ ", getKpiVaueSameForAllRoles()="
				+ getKpiVaueSameForAllRoles() + ", getKpiRoleDTO()="
				+ getKpiRoleDTO() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
}
