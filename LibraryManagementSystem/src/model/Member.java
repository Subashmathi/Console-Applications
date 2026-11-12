package model;

public class Member {
	private String name;
	private int id;
	
	Member(String name, int id){
		this.name = name;
		this.id = id;
	}
	
	public String getName() {return this.name;}
	public int getId() {return this.id;}
}
