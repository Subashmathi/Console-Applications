package service;

import repository.*;
import model.Book;
import java.util.*;
import repository.LibraryRepository;

public class LibraryService {
	
	LibraryRepository repo = new LibraryRepository();
	
	public void addBook(String name, String author, int id) {
		if(!(name != null && author != null && id > 0)) {
			System.out.println("Invalid credentials!");
			return;
		}
		Book book = new Book(name, author, id);
		repo.addBook(book);
	}
	
	public List<Book> displayBooks() {
		List<Book> books = repo.getBooks();
		if(books != null) {
			return books;
		}
		return null;
	}
	
	public Book getBookById(int id) {
		for(Book b : repo.getBooks()) {
			if(b.getId() == id) {
				return b;
			}
		}
		return null;
	}
	
	public Book getBookByName(String name) {
		for(Book b : repo.getBooks()) {
			if(b.getName().equals(name)) {
				return b;
			}
		}
		return null;
	}
}
