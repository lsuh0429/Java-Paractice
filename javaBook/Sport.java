package javaBook;

public class Sport extends Magazine{
	private String web;
	
	public Sport(String id, String isbn, String name, String info, int price,
			String cycle, String web) {
		super(id, isbn, name, info, price, cycle);
		this.web = web;
	}
		
	public void setWeb(String web) {
		this.web = web;
	}
	public String getWeb() {
		return web;
	}
	
	public void show() {
		super.show();
		System.out.println("웹 페이지 연동 : "+this.getWeb());
	}
}
