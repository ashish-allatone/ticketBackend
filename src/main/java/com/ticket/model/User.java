package com.ticket.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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
	private String employeeId = genrateEmployeeId();

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "contact")
	private String contact;

	@Enumerated(EnumType.ORDINAL)
	private UserRole role = UserRole.USER;

	@Column(name = "createdby")
	private String createdby;

	@CreatedDate
	@Column(nullable = false)
	private Timestamp created_timestamp = new Timestamp(Instant.now().toEpochMilli());

	@Column(name = "lastupdatedby")
	private String lastupdatedby;

	@Column(name = "supportidentifier")
	private String supportidentifier = genrateIdentifier();

	@CreatedDate
	@Column(nullable = false)
	private Timestamp last_updated_timestamp = new Timestamp(Instant.now().toEpochMilli());

	@Enumerated(EnumType.ORDINAL)
	private UserServiceType servicetype = UserServiceType.NON_TECHNICAL;

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

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Timestamp getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(Timestamp created_timestamp) {
		this.created_timestamp = created_timestamp;
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

	public Timestamp getLast_updated_timestamp() {
		return last_updated_timestamp;
	}

	public void setLast_updated_timestamp(Timestamp last_updated_timestamp) {
		this.last_updated_timestamp = last_updated_timestamp;
	}

	public UserServiceType getServicetype() {
		return servicetype;
	}

	public void setServicetype(UserServiceType servicetype) {
		this.servicetype = servicetype;
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

	public String genrateEmployeeId() {

		Random random = new Random();
		long minRange = 1000000000L; // Minimum range (inclusive)
		long maxRange = 9999999999L; // Maximum range (inclusive)
		long randomNumber = random.nextLong(maxRange - minRange + 1) + minRange;
		return Long.toString(randomNumber);

	}

	public String genrateIdentifier() {

		Random random = new Random();
		long minRange = 1000000000L; // Minimum range (inclusive)
		long maxRange = 9999999999L; // Maximum range (inclusive)
		long randomNumber = random.nextLong(maxRange - minRange + 1) + minRange;
		return Long.toString(randomNumber);

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", userName=" + userName
				+ ", employeeId=" + employeeId + ", email=" + email + ", contact=" + contact + ", role=" + role
				+ ", createdby=" + createdby + ", created_timestamp=" + created_timestamp + ", lastupdatedby="
				+ lastupdatedby + ", supportidentifier=" + supportidentifier + ", last_updated_timestamp="
				+ last_updated_timestamp + ", servicetype=" + servicetype + ", companyId=" + companyId + "]";
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