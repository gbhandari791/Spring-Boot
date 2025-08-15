package com.springboot.d.jpa.jpql.and.nativequeries;

public class EmpDTO {

	private int id;
	private String name;
		
	public EmpDTO() {
		super();
	}
		
	public EmpDTO(int id, String name) {
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
		return "EmpDTO [id=" + id + ", name=" + name + "]";
	}	
}
