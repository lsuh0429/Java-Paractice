package javaBook;

public class Book {
	private String id;
	private String isbn;
	private String name;
	private String info;
	private int price;
	
	public Book(String id, String isbn, String name, String info, int price) {
		this.id = id;
		this.isbn = isbn;
		this.name = name;
		this.info = info;
		this.price = price;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getName() {
		return name;
	}
	public String getInfo() {
		return info;
	}
	public int getPrice() {
		return price;
	}
	
	public void show() {
		System.out.println("=========================");
		System.out.println("         책 정보                 ");
		System.out.println("식별자 : "+this.getId());
		System.out.println("ISBN : "+this.getIsbn());
		System.out.println("제목 : "+this.getName());
		System.out.println("설명 : "+this.getInfo());
		System.out.println("가격 : "+this.getPrice()+"원");
	}
	
}
