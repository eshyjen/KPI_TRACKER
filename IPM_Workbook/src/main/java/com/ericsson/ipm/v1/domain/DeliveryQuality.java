package com.ericsson.ipm.v1.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


// http://forum.linuxcareer.com/threads/84-Use-BASH-script-to-parse-a-line-from-log-file

@Entity
public class DeliveryQuality implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;

	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "PROJECT_TYPE")
	private String projectType;

	@Column(name = "DQI_SCORE")
	private String dqiScore;

	@Column(name = "DPI_SCORE")
	private String dpiScore;

	@Column(name = "DQI_DATE")
	@Temporal(TemporalType.DATE)
	private Date dqiDate;

	@Column(name = "PM_OR_SPM_NAME")
	private String pmOrSpmName;

	@Column(name = "TL_NAME")
	private String tlName;
	
	
	@Column(name = "SW_ARCHITECT_NAME")
	private String swArchitectName;
	
	
	//bi-directional many-to-one association to Userprofile
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="USER_ID")
		private UserProfile userprofile;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProjectType() {
			return projectType;
		}

		public void setProjectType(String projectType) {
			this.projectType = projectType;
		}

		public String getDqiScore() {
			return dqiScore;
		}

		public void setDqiScore(String dqiScore) {
			this.dqiScore = dqiScore;
		}


		public String getDpiScore() {
			return dpiScore;
		}

		public void setDpiScore(String dpiScore) {
			this.dpiScore = dpiScore;
		}

		public Date getDqiDate() {
			return dqiDate;
		}

		public void setDqiDate(Date dqiDate) {
			this.dqiDate = dqiDate;
		}

		public String getPmOrSpmName() {
			return pmOrSpmName;
		}

		public String getTlName() {
			return tlName;
		}

		public void setTlName(String tlName) {
			this.tlName = tlName;
		}

		public String getSwArchitectName() {
			return swArchitectName;
		}

		public void setSwArchitectName(String swArchitectName) {
			this.swArchitectName = swArchitectName;
		}

		public UserProfile getUserprofile() {
			return userprofile;
		}

		public void setUserprofile(UserProfile userprofile) {
			this.userprofile = userprofile;
		}

		public void setPmOrSpmName(String pmOrSpmName) {
			this.pmOrSpmName = pmOrSpmName;
		}


		
		
}
