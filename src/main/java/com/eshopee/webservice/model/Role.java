package com.eshopee.webservice.model;

import java.util.Date;

public class Role {

	private int id;
	private String role;
	private Date creaedAt;

	public Role(int id, String role, Date creaedAt) {
		super();
		this.id = id;
		this.role = role;
		this.creaedAt = creaedAt;
	}

	public Role() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreaedAt() {
		return creaedAt;
	}

	public void setCreaedAt(Date creaedAt) {
		this.creaedAt = creaedAt;
	}

}
