package com.ticket.model;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "otpclass")
public class OtpClass {

	public enum Role {
		ADMIN, MANAGER, USER
	};

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true, name = "otp")
	private String otp;

	@Column(name = "userName")
	private String userName;

	@Column(name = "timestamp")
	private Timestamp timestamp = new Timestamp(Instant.now().toEpochMilli());

	@Column(name = "email")
	private String email;

	@Enumerated(EnumType.ORDINAL)
	private Role role = Role.USER;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getUseremail() {
		return email;
	}

	public void setUseremail(String useremail) {
		this.email = useremail;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public OtpClass() {
		super();

	}

	@Override
	public String toString() {
		return "OtpClass [id=" + id + ", otp=" + otp + ", username=" + userName + ", timestamp=" + timestamp
				+ ", email=" + email + ", role=" + role + "]";
	}

}
