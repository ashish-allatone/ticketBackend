package com.ticket.model;

import org.springframework.data.annotation.CreatedDate;

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

	@Column(name = "sr_no")
	private String sr_no;

	@Column(name = "username")
	private String username;

	@Column(name = "employeeId")
	private String employeeId;

	@Column(name = "problemsummary")
	private String problemsummary;

	@CreatedDate
	@Column(nullable = false)
	private String last_updated_timestamp = DateTime.dateTime();

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

	public String getSr_no() {
		return sr_no;
	}

	public void setSr_no(String sr_no) {
		this.sr_no = sr_no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProblemsummary() {
		return problemsummary;
	}

	public void setProblemsummary(String problemsummary) {
		this.problemsummary = problemsummary;
	}

	public String getLast_updated_timestamp() {
		return last_updated_timestamp;
	}

	public void setLast_updated_timestamp(String last_updated_timestamp) {
		this.last_updated_timestamp = last_updated_timestamp;
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

	@Override
	public String toString() {
		return "ServiceRequestUpdated [id=" + id + ", sr_no=" + sr_no + ", username=" + username + ", employeeId="
				+ employeeId + ", problemsummary=" + problemsummary + ", last_updated_timestamp="
				+ last_updated_timestamp + ", content=" + content + ", companyId=" + companyId + "]";
	}

	public ServiceRequestUpdated() {
		super();
	}

}
