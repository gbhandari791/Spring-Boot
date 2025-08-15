package com.springboot.d.jpa.jpql.and.nativequeries;

import java.util.List;

import org.springframework.context.ApplicationContext;

public class JpqlUtility {

	public static void performeJpqlAndNativeQueries(ApplicationContext context) {
		
		EmpRepositoryJN empRepo =  context.getBean(EmpRepositoryJN.class);
		
		// By JPQL
		List<Emp> empList = empRepo.getAllEmp();
		empList.forEach(emp -> System.out.println(emp));
		System.out.println("------------------------------------");
		
		List<Emp> empListByTeam = empRepo.getEmpByTeam("Java");
		empListByTeam.forEach(emp -> System.out.println(emp));
		System.out.println("------------------------------------");
		
		List<String> teamByName = empRepo.getTeamByName("Gaurav");
		teamByName.forEach(team -> System.out.println(team));
		System.out.println("------------------------------------");
		
		List<Emp> empByNameAndTeam =  empRepo.getEmpByNamAndTeam("Gaurav", "Java");
		empByNameAndTeam.forEach(emp -> System.out.println(emp));
		System.out.println("------------------------------------");
		
		// 
		List<Object[]> teamAndName = empRepo.getNameAndTeamById();
		teamAndName.forEach(tAndN -> {
			for (int i = 0; i < tAndN.length; i++) {
				String name = (String) tAndN[0];
				String team = (String) tAndN[1];
				System.out.println("name = " + name + " team  = " + team);
			}
		});
		System.out.println("------------------------------------");
		
		List<EmpDTO> empDto = empRepo.getEmpDTO();
		empDto.forEach(emp -> System.out.println(emp));
		System.out.println("------------------------------------");
		
		// By Native Query
		List<Emp> byNativeQuery =  empRepo.getEmpByNativeQuery("Gaurav");
		byNativeQuery.forEach(emp -> System.out.println(emp));
	}
}
