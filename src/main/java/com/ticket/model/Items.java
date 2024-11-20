package com.ticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "items")
@Entity
public class Items {

	public enum Item_Category {
		CLOUD, MANAGE, DEVELOPMENT
	};

	@Id
	@Column(name = "id") // serial Number
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Enumerated(EnumType.ORDINAL)
	private Item_Category itemcategory = Item_Category.CLOUD;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Item_Category getItemcategory() {
		return itemcategory;
	}

	public void setItemcategory(Item_Category itemcategory) {
		this.itemcategory = itemcategory;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", description=" + description + ", itemcategory=" + itemcategory
				+ "]";
	}

	public Items() {
		super();
	}

}
