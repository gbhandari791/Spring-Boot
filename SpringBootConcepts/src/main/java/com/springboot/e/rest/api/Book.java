package com.springboot.e.rest.api;

import org.springframework.stereotype.Component;

public class Book {

	private int id;
	private String name;
	private String Auth;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int id, String naem, String auth) {
		super();
		this.id = id;
		this.name = naem;
		Auth = auth;
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
	public void setName(String naem) {
		this.name = naem;
	}
	public String getAuth() {
		return Auth;
	}
	public void setAuth(String auth) {
		Auth = auth;
	}
	
	
}
