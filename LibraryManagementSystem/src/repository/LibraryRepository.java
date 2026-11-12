package repository;

import java.util.*;
import model.Book;

public class LibraryRepository {
	List<Book> books = new ArrayList<>();
	
	public void addBook(Book book) {
		if(book == null) {
			System.out.println("Invalid book");
			return;
		}
		books.add(book);
		System.out.println("Book added successfully");
	}
	
	public List<Book> getBooks(){
		return books;
	}
}
