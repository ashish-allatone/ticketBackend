package com.ticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "url")
@Entity()
public class URL {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "url", unique = true)
	private String url;

	@Column(name = "groupedBy")
	private String groupedBy;

	@Column(name = "fNameUrl")
	private String fNameUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGroupedBy() {
		return groupedBy;
	}

	public void setGroupedBy(String groupedBy) {
		this.groupedBy = groupedBy;
	}

	public String getfNameUrl() {
		return fNameUrl;
	}

	public void setfNameUrl(String fNameUrl) {
		this.fNameUrl = fNameUrl;
	}

	@Override
	public String toString() {
		return "URL [id=" + id + ", url=" + url + ", groupedBy=" + groupedBy + ", fNameUrl=" + fNameUrl + "]";
	}

	public URL() {
		super();
	}

}
