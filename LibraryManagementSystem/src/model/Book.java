package model;

public class Book {
	
	private String name;
	private String author;
	private int id;
	
	public Book(String name, String author, int id){
		this.name = name;
		this.author = author;
		this.id = id;
	}
	
	public String getName() {return this.name;}
	public String getAuthor() {return this.author;}
	public int getId() {return this.id;}
}
