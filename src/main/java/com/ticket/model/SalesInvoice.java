package com.ticket.model;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "salesinvoice")
@Entity()

public class SalesInvoice {

	public enum Status {
		PAID, PARTIALY_PAID, PENDING
	};

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id; // serial number

	@Column(name = "ponumber") // purchase order number from purchase order table
	private String ponumber;

	@Column(name = "customerid")
	private Integer customerid;

	@Column(name = "itemid")
	private String itemid; // unique id of item from items table

	@Column(name = "totalamount")
	private String totalamount; // unique id of item from items table

	@CreatedDate
	@Column(nullable = false)
	private Timestamp date = new Timestamp(Instant.now().toEpochMilli());

	@Enumerated(EnumType.ORDINAL)
	private Status status = Status.PENDING;

	@Column(name = "comment")
	private String comment;

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

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "SalesInvoice [id=" + id + ", ponumber=" + ponumber + ", customerid=" + customerid + ", itemid=" + itemid
				+ ", totalamount=" + totalamount + ", date=" + date + ", status="
				+ status + ", comment=" + comment + "]";
	}

}
