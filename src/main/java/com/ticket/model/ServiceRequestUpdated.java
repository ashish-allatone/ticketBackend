package com.ticket.model;

import com.ticket.Util.DateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ServiceRequestUpdated")

public class ServiceRequestUpdated {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "serviceRequestNumber")
	private String serviceRequestNumber;

	@Column(name = "userName")
	private String userName;

	@Column(name = "employeeId")
	private String employeeId;

	@Column(name = "problemSummary")
	private String problemSummary;

	@Column(nullable = false)
	private String lastUpdatedTime = DateTime.dateTime();

	@Column(name = "content")
	private String content;

	@Column(name = "companyId")
	private String companyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProblemSummary() {
		return problemSummary;
	}

	public void setProblemSummary(String problemSummary) {
		this.problemSummary = problemSummary;
	}

	@Override
	public String toString() {
		return "ServiceRequestUpdated [id=" + id + ", serviceRequestNumber=" + serviceRequestNumber + ", userName="
				+ userName + ", employeeId=" + employeeId + ", problemSummary=" + problemSummary + ", lastUpdatedTime="
				+ lastUpdatedTime + ", content=" + content + ", companyId=" + companyId + "]";
	}

	public ServiceRequestUpdated() {
		super();
	}

}
