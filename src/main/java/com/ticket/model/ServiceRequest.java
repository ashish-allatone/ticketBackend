package com.ticket.model;

import com.ticket.Util.DateTime;
import com.ticket.Util.RandomNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServiceRequest")
public class ServiceRequest {

	public enum UserIssueType {
		DATABASE, CLOUD, NETWORK, APPLICATION
	};

	public enum UserSevirity {
		CRITICAL, MEDIUM, LOWER
	};

	public enum UserEnvironment {
		PRODUCTION, UID, DEVELOPMENT, DATABASE
	};

	public enum UserStatus {
		PANDING, INPROGRESS, CLOSE
	};

	public enum BusinessImpact {
		NON_TECHNICAL, TECHNICAL
	};

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "serviceRequestNumber")
	private String serviceRequestNumber = RandomNumber.randomNumber();

	@Column(name = "problemSummary")
	private String problemSummary;

	@Column(name = "problemDescreption")
	private String problemDescreption;

	@Column(name = "errorCode")
	private Integer errorCode;

	@Enumerated(EnumType.ORDINAL)
	private UserIssueType issueType = UserIssueType.DATABASE;

	@Enumerated(EnumType.ORDINAL)
	private UserEnvironment environment = UserEnvironment.DEVELOPMENT;

	@Enumerated(EnumType.ORDINAL)
	private UserSevirity sevirity = UserSevirity.LOWER;

	@Enumerated(EnumType.ORDINAL)
	private UserStatus status = UserStatus.PANDING;

	@Enumerated(EnumType.ORDINAL)
	private BusinessImpact businessImpact = BusinessImpact.NON_TECHNICAL;

	@Column(nullable = false)
	private String last_updated_timestamp = DateTime.dateTime();

	@Column(name = "lastupdatedby")
	private String lastupdatedby;

	@Column(name = "contact")
	private String contact;

	@Column(name = "companyId")
	private String companyId;

	@Column(name = "assignToUserName")
	private String assignToUserName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAssignToUserName() {
		return assignToUserName;
	}

	public void setAssignToUserName(String assignToUserName) {
		this.assignToUserName = assignToUserName;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public String getProblemSummary() {
		return problemSummary;
	}

	public void setProblemSummary(String problemSummary) {
		this.problemSummary = problemSummary;
	}

	public String getProblemDescreption() {
		return problemDescreption;
	}

	public void setProblemDescreption(String problemDescreption) {
		this.problemDescreption = problemDescreption;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public UserIssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(UserIssueType issueType) {
		this.issueType = issueType;
	}

	public UserEnvironment getEnvironment() {
		return environment;
	}

	public void setEnvironment(UserEnvironment environment) {
		this.environment = environment;
	}

	public UserSevirity getSevirity() {
		return sevirity;
	}

	public void setSevirity(UserSevirity sevirity) {
		this.sevirity = sevirity;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public BusinessImpact getBusinessImpact() {
		return businessImpact;
	}

	public void setBusinessImpact(BusinessImpact businessImpact) {
		this.businessImpact = businessImpact;
	}

	public String getLast_updated_timestamp() {
		return last_updated_timestamp;
	}

	public void setLast_updated_timestamp(String last_updated_timestamp) {
		this.last_updated_timestamp = last_updated_timestamp;
	}

	@Override
	public String toString() {
		return "ServiceRequest [id=" + id + ", serviceRequestNumber=" + serviceRequestNumber + ", problemSummary="
				+ problemSummary + ", problemDescreption=" + problemDescreption + ", errorCode=" + errorCode
				+ ", issueType=" + issueType + ", environment=" + environment + ", sevirity=" + sevirity + ", status="
				+ status + ", businessImpact=" + businessImpact + ", last_updated_timestamp=" + last_updated_timestamp
				+ ", lastupdatedby=" + lastupdatedby + ", contact=" + contact + ", companyId=" + companyId
				+ ", assignToUserName=" + assignToUserName + "]";
	}

	public ServiceRequest() {
		super();
	}

}
