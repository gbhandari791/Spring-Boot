package com.springboot.e.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("restApiController")
public class TestController {
	
	@Autowired
	BookComponent bookComponent;
	
	@GetMapping("/books")
	public List<Book> books() {
		List<Book> listBook = bookComponent.getAllBooks();
		return listBook;
	}
	
	@GetMapping("/books/{id}")
	public Book book(@PathVariable("id") int id) {
		Book book =  bookComponent.getSigleBook(id);
		return book;
	}
	
	@PostMapping("/books")
	public Book postBook(@RequestBody Book book) {
		return bookComponent.postBook(book);
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable("id") int id) {
		bookComponent.deleteBook(id);
	}
	
	@PutMapping("/books/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable("id") int id) {
		return bookComponent.updateBook(book, id);
	}

}
