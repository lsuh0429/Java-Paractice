package javaBook;

public class Hobby extends Book{
	String gift;
	
	public Hobby(String id, String isbn, String name, String info, int price,
			String gift) {
		super(id, isbn, name, info, price);
		this.gift = gift;
	}
	
	public void setGift(String gift) {
		this.gift = gift;
	}
	public String getGift() {
		return gift;
	}
	
	public void show() {
		super.show();
		System.out.println("사은품 : "+this.getGift());
	}
}
