package com.ticket.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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

@Component
@Entity
@Table(name = "User")

public class User implements UserDetails {

	private static final long serialVersionUID = -7866906556335725442L;

	public enum UserRole {
		ADMIN, USER, MANAGER, SUPERADMIN, SUPERUSER, SUPERMANAGER
	};

	public enum UserServiceType {
		NON_TECHNICAL, TECHNICAL
	};

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "userName", unique = true)
	private String userName;

	@Column(name = "employeeId", unique = true)
	private String employeeId = RandomNumber.randomNumber();

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "contact")
	private String contact;

	@Enumerated(EnumType.ORDINAL)
	private UserRole role = UserRole.USER;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(nullable = false)
	private String creationTime = DateTime.dateTime();

	@Column(name = "lastUpdatedBy")
	private String lastUpdatedBy;

	@Column(nullable = false)
	private String lastUpdatedTime = DateTime.dateTime();

	@Enumerated(EnumType.ORDINAL)
	private UserServiceType serviceType = UserServiceType.NON_TECHNICAL;

	@Column(name = "companyId")
	private String companyId;

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public UserServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(UserServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", userName=" + userName
				+ ", employeeId=" + employeeId + ", email=" + email + ", contact=" + contact + ", role=" + role
				+ ", createdBy=" + createdBy + ", creationTime=" + creationTime + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdatedTime=" + lastUpdatedTime + ", serviceType=" + serviceType + ", companyId=" + companyId
				+ "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}