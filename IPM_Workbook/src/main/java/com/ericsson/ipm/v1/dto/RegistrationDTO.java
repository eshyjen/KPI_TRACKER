package com.ericsson.ipm.v1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ericsson.ipm.v1.validator.PasswordMatches;
import com.ericsson.ipm.v1.validator.ValidEmail;

@PasswordMatches
public class RegistrationDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	
	
	private int id;
	
	@NotNull
    @NotEmpty
	private String userFristName;
	
	@NotNull
    @NotEmpty
	private String userLastName;
	
	@ValidEmail
	@NotNull
    @NotEmpty
	private String emailId;
	
	@NotNull
    @NotEmpty
	private String password;
	
	@NotNull
    @NotEmpty
    private String matchingPassword;

	private String costCenter;
	
	private Boolean role = Boolean.FALSE;
	
	private String currentLineManager;
	
	private Date dateOfJoinInMediaAccount;
	
	private String educationalQualification;
	
	private String employeeId;
	
	private String jobRole;
	
	private String jobStage;
	
	private String lastYearIPMRating;
	
	private String manHourRate;
	
	private Date modifiedDate;
	
	private String previousLineManeger;
	
	private String previousOrganisation;
	
	private Date registrationDate;
	
	private String signunId;
	
	private int totalEricssonExperienceInMonths;
	
	private double totalITExperience;
	
	private double totalYearsOfExperience;
	
	private String yearOfIPM;
	
	private String yearOfLastPromotion;
	
	private String captchaCode;
	
	private Integer userSelectedRole;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserFristName() {
		return userFristName;
	}

	public void setUserFristName(String userFristName) {
		this.userFristName = userFristName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	public String getCurrentLineManager() {
		return currentLineManager;
	}

	public void setCurrentLineManager(String currentLineManager) {
		this.currentLineManager = currentLineManager;
	}

	public Date getDateOfJoinInMediaAccount() {
		return dateOfJoinInMediaAccount;
	}

	public void setDateOfJoinInMediaAccount(Date dateOfJoinInMediaAccount) {
		this.dateOfJoinInMediaAccount = dateOfJoinInMediaAccount;
	}

	public String getEducationalQualification() {
		return educationalQualification;
	}

	public void setEducationalQualification(String educationalQualification) {
		this.educationalQualification = educationalQualification;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getJobRole() {
		return jobRole;
	}

	public void setJobRole(String jobRole) {
		this.jobRole = jobRole;
	}

	public String getJobStage() {
		return jobStage;
	}

	public void setJobStage(String jobStage) {
		this.jobStage = jobStage;
	}

	public String getLastYearIPMRating() {
		return lastYearIPMRating;
	}

	public void setLastYearIPMRating(String lastYearIPMRating) {
		this.lastYearIPMRating = lastYearIPMRating;
	}

	public String getManHourRate() {
		return manHourRate;
	}

	public void setManHourRate(String manHourRate) {
		this.manHourRate = manHourRate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getPreviousLineManeger() {
		return previousLineManeger;
	}

	public void setPreviousLineManeger(String previousLineManeger) {
		this.previousLineManeger = previousLineManeger;
	}

	public String getPreviousOrganisation() {
		return previousOrganisation;
	}

	public void setPreviousOrganisation(String previousOrganisation) {
		this.previousOrganisation = previousOrganisation;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getSignunId() {
		return signunId;
	}

	public void setSignunId(String signunId) {
		this.signunId = signunId;
	}

	public int getTotalEricssonExperienceInMonths() {
		return totalEricssonExperienceInMonths;
	}

	public void setTotalEricssonExperienceInMonths(
			int totalEricssonExperienceInMonths) {
		this.totalEricssonExperienceInMonths = totalEricssonExperienceInMonths;
	}

	public double getTotalITExperience() {
		return totalITExperience;
	}

	public void setTotalITExperience(double totalITExperience) {
		this.totalITExperience = totalITExperience;
	}

	public double getTotalYearsOfExperience() {
		return totalYearsOfExperience;
	}

	public void setTotalYearsOfExperience(double totalYearsOfExperience) {
		this.totalYearsOfExperience = totalYearsOfExperience;
	}

	public String getYearOfIPM() {
		return yearOfIPM;
	}

	public void setYearOfIPM(String yearOfIPM) {
		this.yearOfIPM = yearOfIPM;
	}

	public String getYearOfLastPromotion() {
		return yearOfLastPromotion;
	}

	public void setYearOfLastPromotion(String yearOfLastPromotion) {
		this.yearOfLastPromotion = yearOfLastPromotion;
	}
	
	

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}
	
	

	public Integer getUserSelectedRole() {
		return userSelectedRole;
	}

	public void setUserSelectedRole(Integer userSelectedRole) {
		this.userSelectedRole = userSelectedRole;
	}

	@Override
	public String toString() {
		return "RegistrationDTO [id=" + id + ", userFristName=" + userFristName
				+ ", userLastName=" + userLastName + ", emailId=" + emailId
				+ ", password=" + password + ", matchingPassword="
				+ matchingPassword + ", costCenter=" + costCenter + ", role="
				+ role + ", currentLineManager=" + currentLineManager
				+ ", dateOfJoinInMediaAccount=" + dateOfJoinInMediaAccount
				+ ", educationalQualification=" + educationalQualification
				+ ", employeeId=" + employeeId + ", jobRole=" + jobRole
				+ ", jobStage=" + jobStage + ", lastYearIPMRating="
				+ lastYearIPMRating + ", manHourRate=" + manHourRate
				+ ", modifiedDate=" + modifiedDate + ", previousLineManeger="
				+ previousLineManeger + ", previousOrganisation="
				+ previousOrganisation + ", registrationDate="
				+ registrationDate + ", signunId=" + signunId
				+ ", totalEricssonExperienceInMonths="
				+ totalEricssonExperienceInMonths + ", totalITExperience="
				+ totalITExperience + ", totalYearsOfExperience="
				+ totalYearsOfExperience + ", yearOfIPM=" + yearOfIPM
				+ ", yearOfLastPromotion=" + yearOfLastPromotion + "]";
	}

	
	
	
}
