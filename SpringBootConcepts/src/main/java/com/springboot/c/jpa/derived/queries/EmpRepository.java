package com.springboot.c.jpa.derived.queries;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmpRepository extends CrudRepository<Emp, Integer>{

	public List<Emp> findByName(String name);
	public List<Emp> findByNameIsNotNull();
	public List<Emp> findByNameStartingWith(String prefix);
	public List<Emp> findByNameEndingWith(String suffix);
	public List<Emp> findByNameContaining(String infix);
	public List<Emp> findByNameAndTech(String name, String tech);
	public List<Emp> findByNameAndTeam(String name, String team);
}
