package com.ticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="groupedBy")
@Entity()
public class GroupedBy {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "groupedBy")
	private String groupedBy;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupedBy() {
		return groupedBy;
	}

	public void setGroupedBy(String groupedBy) {
		this.groupedBy = groupedBy;
	}

	@Override
	public String toString() {
		return "GroupedBy [id=" + id + ", groupedBy=" + groupedBy + "]";
	}

	public GroupedBy() {
		super();
	}

}
