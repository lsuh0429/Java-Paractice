package javaTest.bank;
import java.util.*;

public class createAccount extends info {
	public createAccount() {
		System.out.println("계좌 생성에 필요한 정보를 입력해주세요.");
		System.out.print("성명: ");
		BankTest.scanner.nextLine();
		this.setName(BankTest.scanner.nextLine());
		System.out.print("휴대폰 번호(ex.000-0000-0000): ");
		this.setPhone(BankTest.scanner.nextLine());
		this.setMoney(0);
		this.setAccount((int)(Math.random()*90000)+10000 + "");
		
		while(true) {
			if(BankTest.map.containsKey(this.getAccount())) {
				this.setAccount((int)(Math.random()*90000)+10000 + "");
			}
			else
				break;
		}
		
		System.out.println("생성된 계좌번호는 " + this.getAccount()+ "입니다.");
	}
}
