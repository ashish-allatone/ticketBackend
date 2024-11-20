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

@Table(name = "purchaseinvoice")
@Entity()
public class PurchaseInvoice {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id; // serial number

	@Column(name = "ponumber") // purchase order number from purchase order table
	private String ponumber;

	@Column(name = "invoicenumber") // auto generated
	private String invoicenumber;

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

	public String getInvoicenumber() {
		return invoicenumber;
	}

	public void setInvoicenumber(String invoicenumber) {
		this.invoicenumber = invoicenumber;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PurchaseInvoice [id=" + id + ", ponumber=" + ponumber + ", invoicenumber=" + invoicenumber + ", date="
				+ date + "]";
	}

	public PurchaseInvoice() {
		super();
	}

}
