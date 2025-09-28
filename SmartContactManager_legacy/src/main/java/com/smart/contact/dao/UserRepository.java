package com.smart.contact.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.contact.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}
