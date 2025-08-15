package com.springboot.f.rest.api.jpa.with.respoceentity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "book1")
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private int id;
	@Column(name = "book_name")
	private String name;
	@Column(name = "book_auth")
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
	public void setName(String name) {
		this.name = name;
	}
	public String getAuth() {
		return Auth;
	}
	public void setAuth(String auth) {
		Auth = auth;
	}
}
