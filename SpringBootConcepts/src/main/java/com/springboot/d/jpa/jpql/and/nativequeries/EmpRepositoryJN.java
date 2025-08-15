package com.springboot.d.jpa.jpql.and.nativequeries;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EmpRepositoryJN extends CrudRepository<Emp, Integer> { 
	
	//JPQL 
	@Query("Select e from jnEmp e")
	List<Emp> getAllEmp();
	
	@Query("Select e from jnEmp e where e.team = :team")
	List<Emp> getEmpByTeam(@Param("team") String team);
	
	@Query("Select e.team from jnEmp e where e.name = :name")
	List<String> getTeamByName(@Param("name") String name);
	
	@Query("Select e From jnEmp e Where e.name = :n and e.team = :t")
	List<Emp> getEmpByNamAndTeam(@Param("n") String name, @Param("t") String team);
	
	// It will return the data of multiple columns with different DataTypes
	// So we will consider them all as Object and store all columns of one row in  array of Object 
	// and there might be more than one row, so will will store those array in Lists
	@Query("select e.name, e.team from jnEmp e")
	List<Object[]> getNameAndTeamById();
	
	@Query("select new com.springboot.d.jpa.jpql.and.nativequeries.EmpDTO(e.id, e.name) from jnEmp e")
	List<EmpDTO> getEmpDTO();
	 
	// 		Native Query
	@Query(value = "Select * from emp where name = ?", nativeQuery = true)
	public List<Emp> getEmpByNativeQuery(String name);
	
}
