package com.springboot.g.rest.api.jpa.onetoone;

import java.util.ArrayList;
import java.util.List;import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("oneToOneController")
@RequestMapping("/oneToOne")
public class TestController {
	
	@Autowired
	BookComponent bookComponent;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> books() {
		List<Book> listBook = new ArrayList<>();
		try {
			listBook = bookComponent.getAllBooks();
			if(listBook.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.of(Optional.of(listBook));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	
		
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> book(@PathVariable("id") int id) {
		try {
			Book book =  bookComponent.getSigleBook(id);
			if(book == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			return ResponseEntity.of(Optional.of(book));
		} catch (Exception e) {
			if(e.getMessage().equalsIgnoreCase("not found")) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.internalServerError().build();
		}
		
		
		
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> postBook(@RequestBody Book book) {
		try {
		 Book b = bookComponent.postBook(book);
		 if(b == null) {
			 // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); OR
			 return ResponseEntity.internalServerError().build();
		 }
		// return ResponseEntity.of(Optional.of(b))  OR
		 return ResponseEntity.ok(b);

		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
		try {
			bookComponent.deleteBook(id);
			// return ResponseEntity.ok().build(); 		OR
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
		
		try {
			Book b = bookComponent.updateBook(book, id);
			if(b == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			return ResponseEntity.ok(b);
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().equalsIgnoreCase("resource not found")) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.internalServerError().build();
		}
	}

}
