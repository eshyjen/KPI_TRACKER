/**
 * 
 */
package com.ericsson.ipm.v1.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author ihkhan
 * 
 */
@Entity
public class Role implements Serializable, Comparable<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5964441133117272863L;
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	
	@Column(nullable = false, name="NAME")
	private String name;
	
	@Column(nullable = false, name="CODE")
	private String code;

	/*@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "applicationId")
	private Application application;
	*/
	//@Column(updatable = false, columnDefinition = "BIT", length = 1)
	@Column(nullable = false, name="IS_ASSIGNABLE")
	private Boolean isAssignable; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
	private List<KPIRoleAssignment> kpiRoleAssignments = new ArrayList<KPIRoleAssignment>(0);

	
	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public Role(Integer id) {
		super();
		this.id = id;
	}

	public Role(Integer id, String name) {
		this(id);
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean isAssignable() {
		return isAssignable;
	}

	public void setAssignable(Boolean isAssignable) {
		this.isAssignable = isAssignable;
	}
	
	

	public List<KPIRoleAssignment> getKpiRoleAssignments() {
		return kpiRoleAssignments;
	}

	public void setKpiRoleAssignments(List<KPIRoleAssignment> kpiRoleAssignments) {
		this.kpiRoleAssignments = kpiRoleAssignments;
	}

	@Override
	public int compareTo(Role o) {
		Role other = (Role) o;
		return new CompareToBuilder().append(this.name, other.name)
				.toComparison();
	}
}
