package com.springboot.g.rest.api.jpa.onetoone;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("oneToOneBookComponent")
public class BookComponent {
	
	@Autowired
	private BookRepositoryOTO bookRepo;

	private static List<Book> listBook = new ArrayList<>();
//	static {
////		listBook.add(new Book(1, "Java", "Gaurav"));
////		listBook.add(new Book(2, "Spring", "Dev"));
////		listBook.add(new Book(3, "Humen Resource", "Diksha"));
//	}
	
	public List<Book> getAllBooks() throws Exception {
		List<Book> listBook = (List<Book>) bookRepo.findAll();
		return listBook;
	}
	
	public Book getSigleBook(int id) throws Exception  {
		Optional<Book> oBook = bookRepo.findById(id);
		if(oBook.isEmpty()) {
			throw new Exception("not found");
		}
		return oBook.get();
	}
	
	public Book postBook(Book book) {
		try {
			Book b = bookRepo.save(book);
			return b;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void deleteBook(int id) throws Exception {
		bookRepo.deleteById(id);
	}
	
	public Book updateBook(Book book, int id) throws Exception {
			Book rB = null;
			Optional<Book> Ob = bookRepo.findById(id);
			if(Ob.isEmpty()) {
				throw new Exception("resource not found");
			}
			Book dbBook = Ob.get();
			if(dbBook != null) {
				dbBook.setName(book.getName());
				Author auth = dbBook.getAuth();
				auth.setFirstName(book.getAuth().getFirstName());
				auth.setLastName(book.getAuth().getLastName());
				rB = bookRepo.save(dbBook);
			} 
			
		return rB;
	}
			
}
