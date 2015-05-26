package com.ericsson.ipm.v1.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the operationaldiscpline database table.
 *
 */
@Entity
@NamedQuery(name="OperationalDiscipline.findAll", query="SELECT o FROM OperationalDiscipline o")
public class OperationalDiscipline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;



	//bi-directional many-to-one association to Userprofile
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="USER_ID")
		private UserProfile userprofile;

	@Column(name = "OPERATIONAL_DISCIPLINE_NAME")
	private String operationalDisciplineName;

	@Column(name = "FREQUENCY")
	private String frequency;

	@Column(name = "QUARTER")
	private String quarter;

	@Column(name = "NON_COMPLIANCE")
	private String nonCompliance;





	public OperationalDiscipline() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	

	public String getOperationalDisciplineName() {
		return operationalDisciplineName;
	}

	public void setOperationalDisciplineName(String operationalDisciplineName) {
		this.operationalDisciplineName = operationalDisciplineName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getNonCompliance() {
		return nonCompliance;
	}

	public void setNonCompliance(String nonCompliance) {
		this.nonCompliance = nonCompliance;
	}

	public UserProfile getUserprofile() {
		return this.userprofile;
	}

	public void setUserprofile(UserProfile userprofile) {
		this.userprofile = userprofile;
	}

}