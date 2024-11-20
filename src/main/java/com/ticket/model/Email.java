package com.ticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "email")
@Entity()
public class Email {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "groupedBy")
	private String groupedBy;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userEmail")
	private String userEmail;

	@Override
	public String toString() {
		return "Email [id=" + id + ", groupedBy=" + groupedBy + ", userName=" + userName + ", userEmail=" + userEmail
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupedBy() {
		return groupedBy;
	}

	public void setGroupedBy(String groupedBy) {
		this.groupedBy = groupedBy;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Email() {
		super();
	}

}
