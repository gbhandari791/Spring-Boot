package com.springboot.i.modelmapper;

public class EmployeEntity {

	private int id;
	private String name;

	
	public EmployeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "EmployeEntity [id=" + id + ", name=" + name + "]";
	}
	
	
	
}
