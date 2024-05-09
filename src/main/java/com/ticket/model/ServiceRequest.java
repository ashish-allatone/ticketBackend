package com.ticket.model;

import org.springframework.data.annotation.CreatedDate;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

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

	@Column(name = "sr_no")
	private String sr_no = genrateTechnicalSR();

	@Column(name = "problemsummary")
	private String problemsummary;

	@Column(name = "problemdescreption")
	private String problemdescreption;

	@Column(name = "errorcode")
	private Integer errorcode;

	@Enumerated(EnumType.ORDINAL)
	private UserIssueType issuetype = UserIssueType.DATABASE;

	@Enumerated(EnumType.ORDINAL)
	private UserEnvironment environment = UserEnvironment.DEVELOPMENT;

	@Enumerated(EnumType.ORDINAL)
	private UserSevirity sevirity = UserSevirity.LOWER;

	@Enumerated(EnumType.ORDINAL)
	private UserStatus status = UserStatus.PANDING;

	@Enumerated(EnumType.ORDINAL)
	private BusinessImpact businessImpact = BusinessImpact.NON_TECHNICAL;

	@CreatedDate
	@Column(nullable = false)
	private Timestamp last_updated_timestamp = new Timestamp(Instant.now().toEpochMilli());

	@Column(name = "lastupdatedby")
	private String lastupdatedby;

	@Column(name = "supportidentifier")
	private String supportidentifier;

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

	public String getSr_no() {
		return sr_no;
	}

	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}

	public String getProblemsummary() {
		return problemsummary;
	}

	public void setProblemsummary(String problemsummary) {
		this.problemsummary = problemsummary;
	}

	public String getProblemdescreption() {
		return problemdescreption;
	}

	public void setProblemdescreption(String problemdescreption) {
		this.problemdescreption = problemdescreption;
	}

	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}

	public UserIssueType getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(UserIssueType issuetype) {
		this.issuetype = issuetype;
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

	public Timestamp getLast_updated_timestamp() {
		return last_updated_timestamp;
	}

	public void setLast_updated_timestamp(Timestamp last_updated_timestamp) {
		this.last_updated_timestamp = last_updated_timestamp;
	}

	public String getLastupdatedby() {
		return lastupdatedby;
	}

	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}

	public String getSupportidentifier() {
		return supportidentifier;
	}

	public void setSupportidentifier(String supportidentifier) {
		this.supportidentifier = supportidentifier;
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

	@Override
	public String toString() {
		return "ServiceRequest [id=" + id + ", sr_no=" + sr_no + ", problemsummary=" + problemsummary
				+ ", problemdescreption=" + problemdescreption + ", errorcode=" + errorcode + ", issuetype=" + issuetype
				+ ", environment=" + environment + ", sevirity=" + sevirity + ", status=" + status + ", businessImpact="
				+ businessImpact + ", last_updated_timestamp=" + last_updated_timestamp + ", lastupdatedby="
				+ lastupdatedby + ", supportidentifier=" + supportidentifier + ", contact=" + contact + ", companyId="
				+ companyId + ", assignToUserName=" + assignToUserName + "]";
	}

	public String genrateTechnicalSR() {

		Random random = new Random();
		long minRange = 1000000000L; // Minimum range (inclusive)
		long maxRange = 9999999999L; // Maximum range (inclusive)
		long randomNumber = random.nextLong(maxRange - minRange + 1) + minRange;
		return Long.toString(randomNumber);

	}

	public ServiceRequest() {
		super();
	}

}
