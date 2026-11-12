package view;

import java.util.*;
import controller.LibraryController;
import model.Book;

public class LibraryView {
	public void start() {
		Scanner scan = new Scanner(System.in);
		LibraryController cont = new LibraryController();
		while(true) {
			System.out.println("1.Add Book  2.View All Books  3.Search Book By Id  4.Search Book By Name  5.Exit");
			int input = scan.nextInt();
			switch(input) {
			case 1 -> {
				System.out.println("Enter book name: ");
				scan.nextLine();
				String name = scan.nextLine();
				System.out.println("Enter author name: ");
				String author = scan.nextLine();
				System.out.println("Enter book id: ");
				int id = scan.nextInt();
				cont.addBook(name, author, id);
			}
			case 2 -> {
				List<Book> books = cont.displayBook();
				if(books == null) {
					System.out.println("Display Error Occured");
					return;
				}
				for(Book b : books) {
					System.out.printf("%-2s | %-15s | %-15s", b.getId(), b.getName(), b.getAuthor());
					System.out.println();
				}
			}
			case 3 -> {
				System.out.println("Enter id: ");
				int id = scan.nextInt();
				Book b = cont.getBookById(id);
				if(b != null) {
					System.out.printf("%-2s | %-15s | %-15s", b.getId(), b.getName(), b.getAuthor());
					System.out.println();
				}
				else
					System.out.println("No book exist in this ID");
			}
			case 4 -> {
				System.out.println("Enter name: ");
				String name = scan.next();
				Book b = cont.getBookByName(name);
				if(b != null) {
					System.out.printf("%-2s | %-15s | %-15s", b.getId(), b.getName(), b.getAuthor());
					System.out.println();
				}
				else
					System.out.println("No book exist in this name");				
			}
			case 5 -> System.exit(0);
			default -> System.out.println("Invalid Choice!");
			}
		}
	}
}
