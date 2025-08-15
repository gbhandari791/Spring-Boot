package com.springboot.f.rest.api.jpa.with.respoceentity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface BookRepository extends CrudRepository<Book, Integer> {

}
