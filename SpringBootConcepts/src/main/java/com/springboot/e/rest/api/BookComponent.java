package com.springboot.e.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookComponent {

	private static List<Book> listBook = new ArrayList<>();
	static {
		listBook.add(new Book(1, "Java", "Gaurav"));
		listBook.add(new Book(2, "Spring", "Dev"));
		listBook.add(new Book(3, "Humen Resource", "Diksha"));
	}
	
	public List<Book> getAllBooks(){
		return listBook;
	}
	
	public Book getSigleBook(int id) {
		for(Book book : listBook) {
			if(book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	
	public Book postBook(Book book) {
		listBook.add(book);
		return book;
	}
	
	public void deleteBook(int id) {
		for(int i = 0; i < listBook.size() ; i++) {
			if(listBook.get(i).getId() == id) {
				listBook.remove(i);
				break;
			}
		}
	}
	
	public Book updateBook(Book book, int id) {
		
		for(Book b : listBook) {
			if(b.getId() == id) {
				listBook.remove(b);
				listBook.add(book);
				return book;
			}
		}
		return null;
	}
}
