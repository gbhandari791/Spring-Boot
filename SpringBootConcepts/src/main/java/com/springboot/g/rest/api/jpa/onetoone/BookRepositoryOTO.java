package com.springboot.g.rest.api.jpa.onetoone;

import org.springframework.data.repository.CrudRepository;

public interface BookRepositoryOTO extends CrudRepository<Book, Integer> {
	
}
