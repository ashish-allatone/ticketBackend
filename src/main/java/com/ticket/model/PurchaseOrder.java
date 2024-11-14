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

@Table(name = "purchaseorder")
@Entity()
public class PurchaseOrder {

	@Id
	@Column(name = "id") // serial Number
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "ponumber") // 10 digit auto generated number
	private String ponumber = itemNumber();

	@Column(name = "iAmount") // Initial amount
	private String iAmount;

	@Column(name = "itemName")
	private String itemName;

	@Column(name = "description")
	private String description;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "startDate")
	private String startDate;

	@Column(name = "endDate")
	private String endDate;

	@Column(name = "baseAmount")
	private String baseAmount;

	@Column(name = "itemcategory")
	private String itemcategory;

	@Column(name = "remainingAmount")
	private String remainingAmount;

	@Column(name = "amount")
	private String amount;

	@Column(name = "igst")
	private String igst; // other than Delhi state 18%

	@Column(name = "cgst")
	private String cgst; // for only Delhi 9%

	@Column(name = "sgst")
	private String sgst; // for only Delhi 9%

	@Column(name = "totalValue")
	private String totalValue; // for only Delhi 9%

	@CreatedDate
	@Column(nullable = false)
	private Timestamp date = new Timestamp(Instant.now().toEpochMilli());

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPonumber() {
		return ponumber;
	}

	public void setPonumber(String ponumber) {
		this.ponumber = ponumber;
	}

	public String getiAmount() {
		return iAmount;
	}

	public void setiAmount(String iAmount) {
		this.iAmount = iAmount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(String baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public String getIgst() {
		return igst;
	}

	public void setIgst(String igst) {
		this.igst = igst;
	}

	public String getCgst() {
		return cgst;
	}

	public void setCgst(String cgst) {
		this.cgst = cgst;
	}

	public String getSgst() {
		return sgst;
	}

	public void setSgst(String sgst) {
		this.sgst = sgst;
	}

	public String getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemcategory() {
		return itemcategory;
	}

	public void setItemcategory(String itemcategory) {
		this.itemcategory = itemcategory;
	}

	public PurchaseOrder() {
		super();
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", ponumber=" + ponumber + ", iAmount=" + iAmount + ", itemName=" + itemName
				+ ", description=" + description + ", companyName=" + companyName + ", quantity=" + quantity
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", baseAmount=" + baseAmount + ", itemcategory="
				+ itemcategory + ", remainingAmount=" + remainingAmount + ", amount=" + amount + ", igst=" + igst
				+ ", cgst=" + cgst + ", sgst=" + sgst + ", totalValue=" + totalValue + ", date=" + date + "]";
	}

	public String itemNumber() {

		Random random = new Random();
		long minRange = 1000000000L; // Minimum range (inclusive)
		long maxRange = 9999999999L; // Maximum range (inclusive)
		long randomNumber = random.nextLong(maxRange - minRange + 1) + minRange;
		return Long.toString(randomNumber);

	}

}
