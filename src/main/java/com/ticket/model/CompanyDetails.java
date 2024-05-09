package com.ticket.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

import org.springframework.data.annotation.CreatedDate;

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

	@Column(name = "street")
	private String street;

	@Column(name = "landmark")
	private String landmark;

	@Column(name = "officeapartment")
	private String officeapartment;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "pincode")
	private String pinCode;

	@Column(name = "country")
	private String country;

	@Column(name = "companyname")
	private String companyname;

	@Column(name = "gstnumber", unique = true)
	private String gstnumber;

	@Column(name = "companyownername")
	private String companyownername;

	@Column(name = "companycontactno")
	private String companycontactno;

	@Column(name = "pannumber", unique = true)
	private String pannumber;

	@Column(name = "email")
	private String email;

	@Column(name = "companyemail")
	private String companyemail;

	@Column(name = "companyownercontact")
	private String companyownercontact;

	@Column(name = "createdby")
	private String createdby;

	@CreatedDate
	@Column(nullable = false)
	private Timestamp last_updated_timestamp = new Timestamp(Instant.now().toEpochMilli());

	@Column(name = "companyId")
	private String companyId = companyId();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getOfficeapartment() {
		return officeapartment;
	}

	public void setOfficeapartment(String officeapartment) {
		this.officeapartment = officeapartment;
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

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getGstnumber() {
		return gstnumber;
	}

	public void setGstnumber(String gstnumber) {
		this.gstnumber = gstnumber;
	}

	public String getCompanyownername() {
		return companyownername;
	}

	public void setCompanyownername(String companyownername) {
		this.companyownername = companyownername;
	}

	public String getPannumber() {
		return pannumber;
	}

	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyemail() {
		return companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail;
	}

	public String getCompanyownercontact() {
		return companyownercontact;
	}

	public void setCompanyownercontact(String companyownercontact) {
		this.companyownercontact = companyownercontact;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Timestamp getLast_updated_timestamp() {
		return last_updated_timestamp;
	}

	public void setLast_updated_timestamp(Timestamp last_updated_timestamp) {
		this.last_updated_timestamp = last_updated_timestamp;
	}

	public String getCompanycontactno() {
		return companycontactno;
	}

	public void setCompanycontactno(String companycontactno) {
		this.companycontactno = companycontactno;
	}

	public String companyId() {

		Random random = new Random();
		long minRange = 1000000000L; // Minimum range (inclusive)
		long maxRange = 9999999999L; // Maximum range (inclusive)
		long randomNumber = random.nextLong(maxRange - minRange + 1) + minRange;
		return Long.toString(randomNumber);

	}

	@Override
	public String toString() {
		return "CompanyDetails [id=" + id + ", street=" + street + ", landmark=" + landmark + ", officeapartment="
				+ officeapartment + ", city=" + city + ", state=" + state + ", pinCode=" + pinCode + ", country="
				+ country + ", companyname=" + companyname + ", gstnumber=" + gstnumber + ", companyownername="
				+ companyownername + ", companycontactno=" + companycontactno + ", pannumber=" + pannumber + ", email="
				+ email + ", companyemail=" + companyemail + ", companyownercontact=" + companyownercontact
				+ ", createdby=" + createdby + ", last_updated_timestamp=" + last_updated_timestamp + ", companyId="
				+ companyId + "]";
	}

	public CompanyDetails() {
		super();
	}

}
