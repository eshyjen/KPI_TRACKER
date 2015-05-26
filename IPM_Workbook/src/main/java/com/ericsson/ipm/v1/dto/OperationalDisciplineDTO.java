package com.ericsson.ipm.v1.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
public class OperationalDisciplineDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3270361677712529188L;

	@NotNull
    @NotEmpty
	private String operationalDisciplineName;

	@NotNull
    @NotEmpty
	private String frequency;

	@NotNull
    @NotEmpty
	private String quarter;

	@NotNull
    @NotEmpty
	private String nonCompliance;

	private String id;
	/**
	 * @return the operationalDisciplineName
	 */
	public String getOperationalDisciplineName() {
		return operationalDisciplineName;
	}
	/**
	 * @param operationalDisciplineName the operationalDisciplineName to set
	 */
	public void setOperationalDisciplineName(String operationalDisciplineName) {
		this.operationalDisciplineName = operationalDisciplineName;
	}
	/**
	 * @return the frequency
	 */
	public String getFrequency() {
		return frequency;
	}
	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	/**
	 * @return the quarter
	 */
	public String getQuarter() {
		return quarter;
	}
	/**
	 * @param quarter the quarter to set
	 */
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	/**
	 * @return the nonCompliance
	 */
	public String getNonCompliance() {
		return nonCompliance;
	}
	/**
	 * @param nonCompliance the nonCompliance to set
	 */
	public void setNonCompliance(String nonCompliance) {
		this.nonCompliance = nonCompliance;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


}
