package controller;

import model.*;
import view.*;
import java.util.*;
import service.*;

public class LibraryController {
	
	LibraryService lis = new LibraryService();
	
	public void addBook(String name, String author, int id) {
		lis.addBook(name, author, id);
	}
	
	public List<Book> displayBook() {
		return lis.displayBooks();
	}
	
	public Book getBookById(int id) {
		return lis.getBookById(id);
	}
	
	public Book getBookByName(String name) {
		return lis.getBookByName(name);
	}
}
