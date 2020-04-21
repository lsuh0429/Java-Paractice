package javaTest.bank;
import java.util.*;

public class info { 
	private String account;
	private String name;
	private String phone;
	private int money;
	
	public boolean checkingAccount(String account) {
		if(BankTest.map.containsKey(account)) {
			return true;
		}
		else {
			System.out.println("일치하는 정보가 없습니다. 메뉴 화면으로 돌아갑니다.");
			return false;
		}
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	public int getMoney() {
		return money;
	}
}

