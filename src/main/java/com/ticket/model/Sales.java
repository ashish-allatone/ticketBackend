package com.ticket.model;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "sales")
@Entity()
public class Sales {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id; // serial number

	@Column(name = "ponumber") // purchase order number from purchase order table
	private String ponumber;

	@Column(name = "customerid")
	private String customerid;

	@Column(name = "itemid")
	private String itemid; // unique id of item from items table

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

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Sales [id=" + id + ", ponumber=" + ponumber + ", customerid=" + customerid + ", itemid=" + itemid
				+ ", date=" + date + "]";
	}

	public Sales() {
		super();
	}

}
