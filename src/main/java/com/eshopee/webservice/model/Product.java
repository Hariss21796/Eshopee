package com.eshopee.webservice.model;

import java.util.Date;

public class Product {

	private int id;
	private String name;
	private double price;
	private String description;
	private Date createdAt;

	public Product(int id, String name, double price, String description, Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
	}

	public Product() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
