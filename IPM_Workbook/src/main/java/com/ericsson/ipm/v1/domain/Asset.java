package com.ericsson.ipm.v1.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the asset database table.
 * 
 */
@Entity
@NamedQuery(name="Asset.findAll", query="SELECT a FROM Asset a")
public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	@Column(name = "APPROVAL_STATUS")
	private String approvalStatus;

	@Column(name = "ASSET_NAME")
	private String assetName;

	@Column(name = "ASSET_SHORT_DESCRIPTION")
	private String assetShortDescription;

	@Column(name = "EFFORT_SAVE")
	private String effortSave;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "REGISTERED_IN_ASSET_PORTAL")
	private String registeredInAssetPortal;

	@Column(name = "REUSED_IN_OTHE_RPROJECTS_NAME")
	private String reusedInOtherProjectsName;
	
	@Column(name = "MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	//bi-directional many-to-one association to Userprofile
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	private UserProfile userprofile;

	public Asset() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetShortDescription() {
		return assetShortDescription;
	}

	public void setAssetShortDescription(String assetShortDescription) {
		this.assetShortDescription = assetShortDescription;
	}

	public String getEffortSave() {
		return effortSave;
	}

	public void setEffortSave(String effortSave) {
		this.effortSave = effortSave;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRegisteredInAssetPortal() {
		return registeredInAssetPortal;
	}

	public void setRegisteredInAssetPortal(String registeredInAssetPortal) {
		this.registeredInAssetPortal = registeredInAssetPortal;
	}

	public String getReusedInOtherProjectsName() {
		return reusedInOtherProjectsName;
	}

	public void setReusedInOtherProjectsName(String reusedInOtherProjectsName) {
		this.reusedInOtherProjectsName = reusedInOtherProjectsName;
	}

	public UserProfile getUserprofile() {
		return this.userprofile;
	}

	public void setUserprofile(UserProfile userprofile) {
		this.userprofile = userprofile;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}