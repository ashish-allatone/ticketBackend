/**
 * 
 */
package com.ticket.model;

/**
 * @author Ashish Allatone
 *
 */

public class AuthenticationResponse {

	private String name;

	private String email;

	private String phone;

	private String userName;

	private String role;

	private String token;

	private String employeeId;

	private String companyId;

	private String supportidentifier;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getSupportidentifier() {
		return supportidentifier;
	}

	public void setSupportidentifier(String supportidentifier) {
		this.supportidentifier = supportidentifier;
	}

	public AuthenticationResponse(User user, String token) {
		super();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getContact();
		this.userName = user.getUserName();
		this.role = user.getRole().name();
		this.token = token;
		this.employeeId = user.getEmployeeId();
		this.companyId = user.getCompanyId();
		this.supportidentifier = user.getSupportidentifier();
	}

}
