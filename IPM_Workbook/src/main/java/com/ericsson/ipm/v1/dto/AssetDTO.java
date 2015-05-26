package com.ericsson.ipm.v1.dto;

import java.io.Serializable;
import java.util.Date;

public class AssetDTO implements Serializable{


	private static final long serialVersionUID = 1L;

	
	
	private int id;

	private String approvalStatus;

	private String assetName;

	private String assetShortDescription;

	private String effortSave;

	private String projectName;

	private String registeredInAssetPortal;

	private String reusedInOtherProjectsName;
	
	private Date creationDate;

	public int getId() {
		return id;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	// private UserProfile userprofile;
	
	
}
