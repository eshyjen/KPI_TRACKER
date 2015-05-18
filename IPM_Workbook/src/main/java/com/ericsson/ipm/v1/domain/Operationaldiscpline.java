package com.ericsson.ipm.v1.domain;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the operationaldiscpline database table.
 * 
 */
@Entity
@NamedQuery(name="Operationaldiscpline.findAll", query="SELECT o FROM Operationaldiscpline o")
public class Operationaldiscpline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "COMPETENCE_ASSESSMENT10")
	private String competenceAssessment10;

	@Column(name = "COMPETENCE_ASSESSMENT11")
	private String competenceAssessment11;

	@Column(name = "COMPETENCE_ASSESSMENT12")
	private String competenceAssessment12;

	@Column(name = "COMPETENCE_ASSESSMENT13")
	private String competenceAssessment13;

	@Column(name = "CV_UPDATES10")
	private String cvUpdates10;

	@Column(name = "CV_UPDATES11")
	private String cvUpdates11;

	@Column(name = "CV_UPDATES12")
	private String cvUpdates12;

	@Column(name = "CV_UPDATES13")
	private String cvUpdates13;

	@Column(name = "MANDATORY_TRAININGS10")
	private String mandatoryTrainings10;

	@Column(name = "MANDATORY_TRAININGS11")
	private String mandatoryTrainings11;

	@Column(name = "MANDATORY_TRAININGS12")
	private String mandatoryTrainings12;

	@Column(name = "MANDATORY_TRAININGS13")
	private String mandatoryTrainings13;

	@Column(name = "NON_COMPLIANCE10")
	private String noCompliance10;

	@Column(name = "NON_COMPLIANCE11")
	private String noCompliance11;

	@Column(name = "NON_COMPLIANCE12")
	private String noCompliance12;

	@Column(name = "NON_COMPLIANCE13")
	private String noCompliance13;

	@Column(name = "NON_COMPLIANCE20")
	private String noCompliance20;

	@Column(name = "NON_COMPLIANCE21")
	private String noCompliance21;

	@Column(name = "NON_COMPLIANCE22")
	private String noCompliance22;

	@Column(name = "NON_COMPLIANCE23")
	private String noCompliance23;

	@Column(name = "NON_COMPLIANCE30")
	private String noCompliance30;

	@Column(name = "NON_COMPLIANCE31")
	private String noCompliance31;

	@Column(name = "NON_COMPLIANCE32")
	private String noCompliance32;
	
	@Column(name = "NON_COMPLIANCE33")
	private String noCompliance33;

	@Column(name = "NON_COMPLIANCE40")
	private String noCompliance40;

	@Column(name = "NON_COMPLIANCE41")
	private String noCompliance41;

	@Column(name = "NON_COMPLIANCE42")
	private String noCompliance42;

	@Column(name = "NON_COMPLIANCE43")
	private String noCompliance43;

	@Column(name = "TIME_EXPENSE_BOOKING10")
	private String timeExpenseBooking10;

	@Column(name = "TIME_EXPENSE_BOOKING11")
	private String timeExpenseBooking11;

	@Column(name = "TIME_EXPENSE_BOOKING12")
	private String timeExpenseBooking12;

	@Column(name = "TIME_EXPENSE_BOOKING13")
	private String timeExpenseBooking13;

	//bi-directional many-to-one association to Userprofile
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private UserProfile userprofile;

	public Operationaldiscpline() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getCompetenceAssessment10() {
		return competenceAssessment10;
	}

	public void setCompetenceAssessment10(String competenceAssessment10) {
		this.competenceAssessment10 = competenceAssessment10;
	}

	public String getCompetenceAssessment11() {
		return competenceAssessment11;
	}

	public void setCompetenceAssessment11(String competenceAssessment11) {
		this.competenceAssessment11 = competenceAssessment11;
	}

	public String getCompetenceAssessment12() {
		return competenceAssessment12;
	}

	public void setCompetenceAssessment12(String competenceAssessment12) {
		this.competenceAssessment12 = competenceAssessment12;
	}

	public String getCompetenceAssessment13() {
		return competenceAssessment13;
	}

	public void setCompetenceAssessment13(String competenceAssessment13) {
		this.competenceAssessment13 = competenceAssessment13;
	}

	public String getCvUpdates10() {
		return cvUpdates10;
	}

	public void setCvUpdates10(String cvUpdates10) {
		this.cvUpdates10 = cvUpdates10;
	}

	public String getCvUpdates11() {
		return cvUpdates11;
	}

	public void setCvUpdates11(String cvUpdates11) {
		this.cvUpdates11 = cvUpdates11;
	}

	public String getCvUpdates12() {
		return cvUpdates12;
	}

	public void setCvUpdates12(String cvUpdates12) {
		this.cvUpdates12 = cvUpdates12;
	}

	public String getCvUpdates13() {
		return cvUpdates13;
	}

	public void setCvUpdates13(String cvUpdates13) {
		this.cvUpdates13 = cvUpdates13;
	}

	public String getMandatoryTrainings10() {
		return mandatoryTrainings10;
	}

	public void setMandatoryTrainings10(String mandatoryTrainings10) {
		this.mandatoryTrainings10 = mandatoryTrainings10;
	}

	public String getMandatoryTrainings11() {
		return mandatoryTrainings11;
	}

	public void setMandatoryTrainings11(String mandatoryTrainings11) {
		this.mandatoryTrainings11 = mandatoryTrainings11;
	}

	public String getMandatoryTrainings12() {
		return mandatoryTrainings12;
	}

	public void setMandatoryTrainings12(String mandatoryTrainings12) {
		this.mandatoryTrainings12 = mandatoryTrainings12;
	}

	public String getMandatoryTrainings13() {
		return mandatoryTrainings13;
	}

	public void setMandatoryTrainings13(String mandatoryTrainings13) {
		this.mandatoryTrainings13 = mandatoryTrainings13;
	}

	public String getNoCompliance10() {
		return noCompliance10;
	}

	public void setNoCompliance10(String noCompliance10) {
		this.noCompliance10 = noCompliance10;
	}

	public String getNoCompliance11() {
		return noCompliance11;
	}

	public void setNoCompliance11(String noCompliance11) {
		this.noCompliance11 = noCompliance11;
	}

	public String getNoCompliance12() {
		return noCompliance12;
	}

	public void setNoCompliance12(String noCompliance12) {
		this.noCompliance12 = noCompliance12;
	}

	public String getNoCompliance13() {
		return noCompliance13;
	}

	public void setNoCompliance13(String noCompliance13) {
		this.noCompliance13 = noCompliance13;
	}

	public String getNoCompliance20() {
		return noCompliance20;
	}

	public void setNoCompliance20(String noCompliance20) {
		this.noCompliance20 = noCompliance20;
	}

	public String getNoCompliance21() {
		return noCompliance21;
	}

	public void setNoCompliance21(String noCompliance21) {
		this.noCompliance21 = noCompliance21;
	}

	public String getNoCompliance22() {
		return noCompliance22;
	}

	public void setNoCompliance22(String noCompliance22) {
		this.noCompliance22 = noCompliance22;
	}

	public String getNoCompliance23() {
		return noCompliance23;
	}

	public void setNoCompliance23(String noCompliance23) {
		this.noCompliance23 = noCompliance23;
	}

	public String getNoCompliance30() {
		return noCompliance30;
	}

	public void setNoCompliance30(String noCompliance30) {
		this.noCompliance30 = noCompliance30;
	}

	public String getNoCompliance31() {
		return noCompliance31;
	}

	public void setNoCompliance31(String noCompliance31) {
		this.noCompliance31 = noCompliance31;
	}

	public String getNoCompliance32() {
		return noCompliance32;
	}

	public void setNoCompliance32(String noCompliance32) {
		this.noCompliance32 = noCompliance32;
	}

	public String getNoCompliance33() {
		return noCompliance33;
	}

	public void setNoCompliance33(String noCompliance33) {
		this.noCompliance33 = noCompliance33;
	}

	public String getNoCompliance40() {
		return noCompliance40;
	}

	public void setNoCompliance40(String noCompliance40) {
		this.noCompliance40 = noCompliance40;
	}

	public String getNoCompliance41() {
		return noCompliance41;
	}

	public void setNoCompliance41(String noCompliance41) {
		this.noCompliance41 = noCompliance41;
	}

	public String getNoCompliance42() {
		return noCompliance42;
	}

	public void setNoCompliance42(String noCompliance42) {
		this.noCompliance42 = noCompliance42;
	}

	public String getNoCompliance43() {
		return noCompliance43;
	}

	public void setNoCompliance43(String noCompliance43) {
		this.noCompliance43 = noCompliance43;
	}

	public String getTimeExpenseBooking10() {
		return timeExpenseBooking10;
	}

	public void setTimeExpenseBooking10(String timeExpenseBooking10) {
		this.timeExpenseBooking10 = timeExpenseBooking10;
	}

	public String getTimeExpenseBooking11() {
		return timeExpenseBooking11;
	}

	public void setTimeExpenseBooking11(String timeExpenseBooking11) {
		this.timeExpenseBooking11 = timeExpenseBooking11;
	}

	public String getTimeExpenseBooking12() {
		return timeExpenseBooking12;
	}

	public void setTimeExpenseBooking12(String timeExpenseBooking12) {
		this.timeExpenseBooking12 = timeExpenseBooking12;
	}

	public String getTimeExpenseBooking13() {
		return timeExpenseBooking13;
	}

	public void setTimeExpenseBooking13(String timeExpenseBooking13) {
		this.timeExpenseBooking13 = timeExpenseBooking13;
	}

	public UserProfile getUserprofile() {
		return this.userprofile;
	}

	public void setUserprofile(UserProfile userprofile) {
		this.userprofile = userprofile;
	}

}