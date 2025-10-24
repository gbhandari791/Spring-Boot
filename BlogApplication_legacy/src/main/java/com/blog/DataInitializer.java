package com.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.blog.constants.AppConstant;
import com.blog.entities.Role;
import com.blog.repositories.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		insertDefaultRoles();
	}
	
	private void insertDefaultRoles() {
		
		List<Role> roles = new ArrayList<>();
		
		if(roleRepo.findById(AppConstant.ID_ROLE_ADMIN).isEmpty()) {
			Role role = new Role();
			role.setId(AppConstant.ID_ROLE_ADMIN);
			role.setRole(AppConstant.ROLE_ADMIN);
			roles.add(role);
		}
		
		if(roleRepo.findById(AppConstant.ID_ROLE_NORMAL).isEmpty()) {
			Role role = new Role();
			role.setId(AppConstant.ID_ROLE_NORMAL);
			role.setRole(AppConstant.ROLE_NORMAL);
			roles.add(role);
		}
		
		if(!roles.isEmpty()) {
			roleRepo.saveAll(roles);
		}
		
	}

}
