package com.ticket.model;

public class UserResponse {

	private String name;

	private String email;

	private String userName;

	private String role;

	private String belong;

	private String contact;

	private String lastUpdateBy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public UserResponse(User empInfo, String token) {
		super();
	}

	@Override
	public String toString() {
		return "UserResponse [name=" + name + ", email=" + email + ", userName=" + userName + ", role=" + role
				+ ", belong=" + belong + ", contact=" + contact
				+ ", lastUpdateBy=" + lastUpdateBy + "]";
	}

}
