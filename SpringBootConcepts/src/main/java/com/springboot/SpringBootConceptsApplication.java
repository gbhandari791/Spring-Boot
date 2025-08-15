package com.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springboot.b.jpa.User;
import com.springboot.b.jpa.UserRepository;
import com.springboot.b.jpa.UserUtility;
import com.springboot.c.jpa.derived.queries.EmployeeUtility;
import com.springboot.d.jpa.jpql.and.nativequeries.JpqlUtility;

@SpringBootApplication
public class SpringBootConceptsApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootConceptsApplication.class, args);
		
		/*JPA Crud*/
		// UserUtility.performJpaCrud(context);
		
		/*Derived Queries*/
		// EmployeeUtility.derivedQueries(context);
		
		/* Jpql and native queries */
		// JpqlUtility.performeJpqlAndNativeQueries(context);

	}

}
