package com.springboot.b.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationContext;

public class UserUtility {

	public static void performJpaCrud(ApplicationContext context) {
		/*
		 * Create Save/Insert Single Object
		 */
		UserRepository userRepo = context.getBean(UserRepository.class);
		User user = new User();
		user.setName("Gaurav");
		user.setAge(23);
		User save = userRepo.save(user);
		
		/* Save Multiple Objects */
		User user1 = new User();		
		user1.setName("John");
		user1.setAge(28);
		
		User user2 = new User();		
		user2.setName("Dev");
		user2.setAge(24);
		
		/*
		 * repository.saveAll() takes Iterable as parameter Iterable is an parent of
		 * Collection so we can provide List, Map or Set of Objects or there Child
		 */
		List<User> userList = List.of(user1, user2);
		Iterable<User> saveAllItr = userRepo.saveAll(userList);
		saveAllItr.forEach(savedUsers -> {
			System.err.println(savedUsers);
		});
		
		
		/*
		 * Read 
		 * fins row by Id
		 */
		Optional<User> optional =  userRepo.findById(1);
		User rUser = optional.get();
		System.out.println(rUser);
		
		/* Find multiple rows by ids */
		List<Integer> listUser = List.of(2, 3, 4);
		Iterable<User> users = userRepo.findAllById(listUser);
		users.forEach(singleUser -> System.out.println(user));
		
		/* Read All Rows */
		Iterable<User> allUsers = userRepo.findAll();
		allUsers.forEach(singleUser -> System.out.println(singleUser));

		/* Update */

		Optional<User> userOption = userRepo.findById(53);
		User uUser = userOption.get();
		uUser.setName("Rutik");
		User resultUser = userRepo.save(uUser);
		System.out.println(resultUser);

		/*
		 * Delete 
		 * Delete single row
		 */
		userRepo.deleteById(1);

		/* Delete multiple rows */
		List<Integer> listOfUser = List.of(2, 3);
		userRepo.deleteAllById(listOfUser);

		
		/* delete by entity */
		Optional<User> dUserOption = userRepo.findById(4);
		User dUser = dUserOption.get();
		userRepo.delete(dUser);

		/* delete mutiple entities */
		Iterable<User> userIter = userRepo.findAll();
		userRepo.deleteAll(userIter);
		
		/* Delete all rows */
		userRepo.deleteAll();
	}
}
