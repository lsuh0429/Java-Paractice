package javaTest.bank;

public class balance extends info {
	public balance() {
		System.out.print("조회할 계좌 번호를 입력해주세요.");
		BankTest.scanner.nextLine();
		this.setAccount(BankTest.scanner.nextLine());
		
		if(this.checkingAccount(this.getAccount())) {
			info temp = BankTest.map.get(this.getAccount());
			System.out.println("현재 잔액은 " + temp.getMoney() + "원 입니다.");
		}
	}
}
