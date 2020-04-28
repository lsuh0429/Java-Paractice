package javaBook;

public class Novel extends Book{
	private String author;
	private String genre;
	
	public Novel(String id, String isbn, String name, String info, int price,
			String author, String genre) {
		super(id, isbn, name, info, price);
		this.author = author;
		this.genre = genre;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public String getGenre() {
		return genre;
	}
	
	public void show() {
		super.show();
		System.out.println("저자 : "+this.getAuthor());
		System.out.println("장르 : "+this.getGenre());
	}
}
