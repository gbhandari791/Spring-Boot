package com.springboot.c.jpa.derived.queries;

import java.util.List;

import org.springframework.context.ApplicationContext;

public class EmployeeUtility {

	public static void derivedQueries(ApplicationContext context) {

		EmpRepository empRepo = context.getBean(EmpRepository.class);

		List<Emp> empListByName = empRepo.findByName("Gaurav");
		empListByName.forEach(emp -> System.out.println(emp));
		System.out.println("----------------------------------------");

		List<Emp> empNotNull = empRepo.findByNameIsNotNull();
		empNotNull.forEach(emp -> System.out.println(emp));
		System.out.println("----------------------------------------");

		List<Emp> empSttingWith = empRepo.findByNameStartingWith("Ga");
		empSttingWith.forEach(emp -> System.out.println(emp));
		System.out.println("----------------------------------------");

		List<Emp> empEndingWith = empRepo.findByNameEndingWith("sha");
		empEndingWith.forEach(emp -> System.out.println(emp));
		System.out.println("----------------------------------------");

		List<Emp> empContaining = empRepo.findByNameContaining("van");
		empContaining.forEach(emp -> System.out.println(emp));
		System.out.println("----------------------------------------");

		List<Emp> naemAndTech = empRepo.findByNameAndTech("Gaurav", "Java");
		naemAndTech.forEach(emp -> System.out.println(emp));
		System.out.println("----------------------------------------");

		List<Emp> nameAndTeam = empRepo.findByNameAndTeam("Diksha", "HR");
		nameAndTeam.forEach(emp -> System.out.println(emp));
		System.out.println("----------------------------------------");
	}
}
