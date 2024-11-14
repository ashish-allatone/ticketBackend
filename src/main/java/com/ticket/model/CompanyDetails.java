package com.ticket.model;

import com.ticket.Util.DateTime;
import com.ticket.Util.RandomNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "CompanyDetails")
@Entity
public class CompanyDetails {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "officeApartment")
	private String officeApartment;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "pinCode")
	private String pinCode;

	@Column(name = "country")
	private String country;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "primaryContactNumber")
	private String primaryContactNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "lastUpdatedBy")
	private String lastUpdatedBy;

	@Column(nullable = false)
	private String creationTime = DateTime.dateTime();

	@Column(nullable = false)
	private String lastUpdationTime = DateTime.dateTime();

	@Column(name = "companyId")
	private String companyId = RandomNumber.randomNumber();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getOfficeApartment() {
		return officeApartment;
	}

	public void setOfficeApartment(String officeApartment) {
		this.officeApartment = officeApartment;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}

	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastUpdationTime() {
		return lastUpdationTime;
	}

	public void setLastUpdationTime(String lastUpdationTime) {
		this.lastUpdationTime = lastUpdationTime;
	}

	

	@Override
	public String toString() {
		return "CompanyDetails [id=" + id + ", officeApartment=" + officeApartment + ", city=" + city + ", state="
				+ state + ", pinCode=" + pinCode + ", country=" + country + ", companyName=" + companyName
				+ ", primaryContactNumber=" + primaryContactNumber + ", email=" + email + ", createdBy=" + createdBy
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", creationTime=" + creationTime + ", lastUpdationTime="
				+ lastUpdationTime + ", companyId=" + companyId + "]";
	}

	public CompanyDetails() {
		super();
	}

}
