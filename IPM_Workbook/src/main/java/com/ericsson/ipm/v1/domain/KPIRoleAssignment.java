package com.ericsson.ipm.v1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author ihkhan
 * 
 */

// http://www.jroller.com/raible/entry/multiple_select_with_spring_mvc
// http://stackoverflow.com/questions/18728678/spring-formselect-multiple-selected-value

@Entity
public class KPIRoleAssignment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue
	@Column(name = "KPI_ROLE_ASSIGNMENT_ID")
	private Integer id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "KPI_ID")
	private KPI kpi;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID")
	private Role role;

	public KPIRoleAssignment() {
		super();
	}

	public KPIRoleAssignment(KPI kpi, Role role) {
		super();
		this.kpi = kpi;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KPI getKpi() {
		return kpi;
	}

	public void setKpi(KPI kpi) {
		this.kpi = kpi;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
