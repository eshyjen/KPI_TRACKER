package com.ericsson.ipm.v1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;


/**
 * @author ihkhan
 * 
 */

@Entity
public class KPI implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "KPI_DISPLAY_NAME")
	private String kpiDisplayName;

	@Column(name = "KPI_NAME1", length=1000)
	private String kpiName1;
	
	@Column(name = "KPI_NAME2", length=1000)
	private String kpiName2;
	
	@Column(name = "KPI_NAME3", length=1000)
	private String kpiName3;
	
	@Column(name = "KPI_NAME4", length=1000)
	private String kpiName4;
	
	@Column(name = "KPI_VALUE")
	private String kpiValue;
	
	@Column(name = "KPI_DESCRIPTION1", length=1000)
	private String kpiDescription1;
	
	@Column(name = "KPI_DESCRIPTION2", length=1000)
	private String kpiDescription2;
	
	@Column(name = "KPI_DESCRIPTION3", length=1000)
	private String kpiDescription3;
	
	@Column(name = "KPI_DESCRIPTION4", length=1000)
	private String kpiDescription4;
	
	@Transient
	private String kpiName;
	
	@Transient
	private String kpiDescription;

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

	public String getKpiName1() {
		return kpiName1;
	}

	public void setKpiName1(String kpiName1) {
		this.kpiName1 = kpiName1;
	}

	public String getKpiName2() {
		return kpiName2;
	}

	public void setKpiName2(String kpiName2) {
		this.kpiName2 = kpiName2;
	}

	public String getKpiName3() {
		return kpiName3;
	}

	public void setKpiName3(String kpiName3) {
		this.kpiName3 = kpiName3;
	}

	public String getKpiName4() {
		return kpiName4;
	}

	public void setKpiName4(String kpiName4) {
		this.kpiName4 = kpiName4;
	}

	public String getKpiValue() {
		return kpiValue;
	}

	public void setKpiValue(String kpiValue) {
		this.kpiValue = kpiValue;
	}

	public String getKpiDescription1() {
		return kpiDescription1;
	}

	public void setKpiDescription1(String kpiDescription1) {
		this.kpiDescription1 = kpiDescription1;
	}

	public String getKpiDescription2() {
		return kpiDescription2;
	}

	public void setKpiDescription2(String kpiDescription2) {
		this.kpiDescription2 = kpiDescription2;
	}

	public String getKpiDescription3() {
		return kpiDescription3;
	}

	public void setKpiDescription3(String kpiDescription3) {
		this.kpiDescription3 = kpiDescription3;
	}

	public String getKpiDescription4() {
		return kpiDescription4;
	}

	public void setKpiDescription4(String kpiDescription4) {
		this.kpiDescription4 = kpiDescription4;
	}

	public String getKpiName() {
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.isNotBlank(getKpiName1()))
			sb.append(getKpiName1());
		if(StringUtils.isNotBlank(getKpiName2()))
			sb.append(getKpiName2());
		if(StringUtils.isNotBlank(getKpiName3()))
			sb.append(getKpiName3());
		if(StringUtils.isNotBlank(getKpiName4()))
			sb.append(getKpiName4());
			
		return kpiName = sb.toString();
	}


	public String getKpiDescription() {
		
		StringBuilder sb = new StringBuilder();
		
		if(StringUtils.isNotBlank(getKpiDescription1()))
			sb.append(getKpiDescription1());
		if(StringUtils.isNotBlank(getKpiDescription2()))
			sb.append(getKpiDescription2());
		if(StringUtils.isNotBlank(getKpiDescription3()))
			sb.append(getKpiDescription3());
		if(StringUtils.isNotBlank(getKpiDescription4()))
			sb.append(getKpiDescription4());
		return kpiDescription = sb.toString();
	}

	@Override
	public String toString() {
		return "KPI [id=" + id + ", kpiDisplayName=" + kpiDisplayName
				+ ", kpiName1=" + kpiName1 + ", kpiName2=" + kpiName2
				+ ", kpiName3=" + kpiName3 + ", kpiName4=" + kpiName4
				+ ", kpiValue=" + kpiValue + ", kpiDescription1="
				+ kpiDescription1 + ", kpiDescription2=" + kpiDescription2
				+ ", kpiDescription3=" + kpiDescription3 + ", kpiDescription4="
				+ kpiDescription4 + ", kpiName=" + kpiName
				+ ", kpiDescription=" + kpiDescription + "]";
	}
	
	
	
	

	
	
}
