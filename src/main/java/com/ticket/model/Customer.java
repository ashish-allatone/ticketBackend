package com.ticket.model;

import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "customer")
@Entity
public class Customer {

	public enum ServiceType {
		CLOUD, MANAGE, DEVELOPMENT
	};

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "companyAddress")
	private String companyAddress;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "gstn")
	private String gstn;

	@Column(name = "pName")
	private String pName;

	@Column(name = "pEmail")
	private String pEmail;

	@Column(name = "pContact")
	private String pContact;

	@Enumerated(EnumType.ORDINAL)
	private ServiceType serviceType = ServiceType.CLOUD;

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", companyName=" + companyName
				+ ", companyAddress=" + companyAddress + ", state=" + state + ", country=" + country + ", gstn=" + gstn
				+ ", pName=" + pName + ", pEmail=" + pEmail + ", pContact=" + pContact + ", serviceType=" + serviceType
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpEmail() {
		return pEmail;
	}

	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}

	public String getpContact() {
		return pContact;
	}

	public void setpContact(String pContact) {
		this.pContact = pContact;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String itemNumber() {

		Random random = new Random();
		long minRange = 1000000000L; // Minimum range (inclusive)
		long maxRange = 9999999999L; // Maximum range (inclusive)
		long randomNumber = random.nextLong(maxRange - minRange + 1) + minRange;
		return Long.toString(randomNumber);

	}

}
