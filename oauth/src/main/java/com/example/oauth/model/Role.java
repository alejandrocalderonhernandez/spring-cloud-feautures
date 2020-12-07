package com.example.oauth.model;

import java.io.Serializable;

public class Role implements Serializable{

	private static final long serialVersionUID = -5346715407899066470L;

	private Long id;
	private String name;
	
	public Role() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
