package com.ericsson.ipm.v1.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the userprofile database table.
 * 
 */
@Entity
@NamedQuery(name="Userprofile.findAll", query="SELECT u FROM UserProfile u")
public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	
	@Column(name = "USER_FRIST_NAME")
	private String userFristName;
	
	@Column(name = "USER_LAST_NAME")
	private String userLastName;
	
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "COST_CENTER")
	private String costCenter;
	
	@Column(name = "IS_ROLE_MANAGER")
	private Boolean role = Boolean.FALSE;
	
	@Column(name = "CURRENT_LINE_MANAGER")
	private String currentLineManager;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_OF_JOIN_IN_MEDIA_ACCOUNT")
	private Date dateOfJoinInMediaAccount;
	
	@Column(name = "EDUCATIONAL_QUALIFICATION")
	private String educationalQualification;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "EMPLOYEE_ID")
	private String employeeId;
	
	@Column(name = "JOB_ROLE")
	private String jobRole;
	
	@Column(name = "JOB_STAGE")
	private String jobStage;
	
	@Column(name = "LASTYEAR_IPM_RATING")
	private String lastYearIPMRating;
	
	@Column(name = "MAN_HOUR_RATE")
	private String manHourRate;
	
	@Column(name = "MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name = "PREVIOUS_LINE_MANEGER")
	private String previousLineManeger;
	
	@Column(name = "PREVIOUS_ORGANISATION")
	private String previousOrganisation;
	
	@Column(name = "REGISTRATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date registrationDate;
	
	@Column(name = "SIGNUN_ID")
	private String signunId;
	
	@Column(name = "TOTAL_ERICSSON_EXPERIENCE_IN_MONTHS")
	private int totalEricssonExperienceInMonths;
	
	@Column(name = "TOTALITEXPERIENCE")
	private double totalITExperience;
	
	@Column(name = "TOTAL_YEARS_OF_EXPERIENCE")
	private double totalYearsOfExperience;
	
	@Column(name = "YEAROF_IPM")
	private String yearOfIPM;
	
	@Column(name = "YEAR_OF_LAST_PROMOTION")
	private String yearOfLastPromotion;
	
	@Column(name = "IS_ENABLED")
	private Boolean isEnabled = Boolean.FALSE;

	//bi-directional many-to-one association to Asset
	@OneToMany(mappedBy="userprofile")
	private List<Asset> assets;

	//bi-directional many-to-one association to Operationaldiscpline
	@OneToMany(mappedBy="userprofile")
	private List<OperationalDiscipline> operationaldiscplines;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserRoleAssignment> roleAssignments = new ArrayList<UserRoleAssignment>(0);
	
	//bi-directional many-to-one association to deliveryQualities
	@OneToMany(mappedBy="userprofile")
	private List<DeliveryQuality> deliveryQualities;

	public UserProfile() {
	}

	public int getId() {
		return this.id;
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

	public List<Asset> getAssets() {
		return this.assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public Boolean getRole() {
		return role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	public Asset addAsset(Asset asset) {
		getAssets().add(asset);
		asset.setUserprofile(this);

		return asset;
	}

	public Asset removeAsset(Asset asset) {
		getAssets().remove(asset);
		asset.setUserprofile(null);

		return asset;
	}

	public List<OperationalDiscipline> getOperationaldiscplines() {
		return this.operationaldiscplines;
	}

	public void setOperationaldiscplines(List<OperationalDiscipline> operationaldiscplines) {
		this.operationaldiscplines = operationaldiscplines;
	}

	public OperationalDiscipline addOperationaldiscpline(OperationalDiscipline operationaldiscpline) {
		getOperationaldiscplines().add(operationaldiscpline);
		operationaldiscpline.setUserprofile(this);

		return operationaldiscpline;
	}

	public OperationalDiscipline removeOperationaldiscpline(OperationalDiscipline operationaldiscpline) {
		getOperationaldiscplines().remove(operationaldiscpline);
		operationaldiscpline.setUserprofile(null);

		return operationaldiscpline;
	}
	
	   public List<UserRoleAssignment> getRoleAssignments() {
		return roleAssignments;
	}

	public void setRoleAssignments(List<UserRoleAssignment> roleAssignments) {
		this.roleAssignments = roleAssignments;
	}

		// convenience-method
		public void addRole(Role role) {
			this.getRoleAssignments().add(new UserRoleAssignment(this, role));
		}

		// convenience-method
		public void addAllRoles(List<Role> roles) {
			for (Role role : roles) {
				addRole(role);
			}
		}

		// convenience-method
		public void clearAllRoles() {
			this.getRoleAssignments().clear();
		}

		// convenience-method
		public List<Role> getRoles() {
			List<Role> userRoles = new ArrayList<Role>();
			for (UserRoleAssignment assignment : this.getRoleAssignments()) {
				userRoles.add(assignment.getRole());
			}
			return userRoles;
		}

		public List<DeliveryQuality> getDeliveryQualities() {
			return deliveryQualities;
		}

		public void setDeliveryQualities(List<DeliveryQuality> deliveryQualities) {
			this.deliveryQualities = deliveryQualities;
		}

		public Boolean getIsEnabled() {
			return isEnabled;
		}

		public void setIsEnabled(Boolean isEnabled) {
			this.isEnabled = isEnabled;
		}
		
		
		

}