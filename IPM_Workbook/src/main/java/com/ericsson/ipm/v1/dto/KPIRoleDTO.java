package com.ericsson.ipm.v1.dto;

import java.io.Serializable;

public class KPIRoleDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String kpiValueSWD;
	private String kpiValueSSWD;
	private String kpiValueSA;
	private String kpiValueSSA;
	private String kpiValueSPM;
	private String kpiValueCA;
	private String kpiValueRPM;
	private String kpiValueSDL;
	private String kpiValueDL;
	private String kpiValue;
	private String kpiDisplayName;
	private String kpiName;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKpiValueSWD() {
		return kpiValueSWD;
	}
	public void setKpiValueSWD(String kpiValueSWD) {
		this.kpiValueSWD = kpiValueSWD;
	}
	public String getKpiValueSSWD() {
		return kpiValueSSWD;
	}
	public void setKpiValueSSWD(String kpiValueSSWD) {
		this.kpiValueSSWD = kpiValueSSWD;
	}
	public String getKpiValueSA() {
		return kpiValueSA;
	}
	public void setKpiValueSA(String kpiValueSA) {
		this.kpiValueSA = kpiValueSA;
	}
	public String getKpiValueSSA() {
		return kpiValueSSA;
	}
	public void setKpiValueSSA(String kpiValueSSA) {
		this.kpiValueSSA = kpiValueSSA;
	}
	public String getKpiValueSPM() {
		return kpiValueSPM;
	}
	public void setKpiValueSPM(String kpiValueSPM) {
		this.kpiValueSPM = kpiValueSPM;
	}
	public String getKpiValueCA() {
		return kpiValueCA;
	}
	public void setKpiValueCA(String kpiValueCA) {
		this.kpiValueCA = kpiValueCA;
	}
	public String getKpiValueRPM() {
		return kpiValueRPM;
	}
	public void setKpiValueRPM(String kpiValueRPM) {
		this.kpiValueRPM = kpiValueRPM;
	}
	public String getKpiValueSDL() {
		return kpiValueSDL;
	}
	public void setKpiValueSDL(String kpiValueSDL) {
		this.kpiValueSDL = kpiValueSDL;
	}
	public String getKpiValueDL() {
		return kpiValueDL;
	}
	public void setKpiValueDL(String kpiValueDL) {
		this.kpiValueDL = kpiValueDL;
	}
	public String getKpiValue() {
		return kpiValue;
	}
	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}
	public String getKpiDisplayName() {
		return kpiDisplayName;
	}
	public void setKpiDisplayName(String kpiDisplayName) {
		this.kpiDisplayName = kpiDisplayName;
	}
	public String getKpiName() {
		return kpiName;
	}
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	@Override
	public String toString() {
		return "KPIRoleDTO [id=" + id + ", kpiValueSWD=" + kpiValueSWD
				+ ", kpiValueSSWD=" + kpiValueSSWD + ", kpiValueSA="
				+ kpiValueSA + ", kpiValueSSA=" + kpiValueSSA
				+ ", kpiValueSPM=" + kpiValueSPM + ", kpiValueCA=" + kpiValueCA
				+ ", kpiValueRPM=" + kpiValueRPM + ", kpiValueSDL="
				+ kpiValueSDL + ", kpiValueDL=" + kpiValueDL + ", kpiValue="
				+ kpiValue + ", kpiDisplayName=" + kpiDisplayName
				+ ", kpiName=" + kpiName + ", getId()=" + getId()
				+ ", getKpiValueSWD()=" + getKpiValueSWD()
				+ ", getKpiValueSSWD()=" + getKpiValueSSWD()
				+ ", getKpiValueSA()=" + getKpiValueSA()
				+ ", getKpiValueSSA()=" + getKpiValueSSA()
				+ ", getKpiValueSPM()=" + getKpiValueSPM()
				+ ", getKpiValueCA()=" + getKpiValueCA()
				+ ", getKpiValueRPM()=" + getKpiValueRPM()
				+ ", getKpiValueSDL()=" + getKpiValueSDL()
				+ ", getKpiValueDL()=" + getKpiValueDL() + ", getKpiValue()="
				+ getKpiValue() + ", getKpiDisplayName()="
				+ getKpiDisplayName() + ", getKpiName()=" + getKpiName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	
	
	

}
