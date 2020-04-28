package javaBook;

public class Magazine extends Book{
	private String cycle;
	
	public Magazine(String id, String isbn, String name, String info, int price,
			String cycle) {
		super(id, isbn, name, info, price);
		this.cycle = cycle;
	}
	
	
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getCycle() {
		return cycle;
	}
	
	public void show() {
		super.show();
		System.out.println("발행 주기 : "+this.getCycle());
	}
}
