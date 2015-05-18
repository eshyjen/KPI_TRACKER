package com.ericsson.ipm.v1.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.ericsson.ipm.v1.domain.UserRoleAssignment;

public class UserProfileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	
	private String userFristName;
	
	private String userLastName;
	
	private String password;

	private String costCenter;
	
	private Boolean role = Boolean.FALSE;
	
	private String currentLineManager;
	
	private Date dateOfJoinInMediaAccount;
	
	private String educationalQualification;
	
	private String emailId;
	
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

	private List<UserRoleAssignment> roleAssignments = new ArrayList<UserRoleAssignment>(0);

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public List<UserRoleAssignment> getRoleAssignments() {
		return roleAssignments;
	}

	public void setRoleAssignments(List<UserRoleAssignment> roleAssignments) {
		this.roleAssignments = roleAssignments;
	}
	
	

}
