package javaTest.bank;

public class withdraw extends info {
	public withdraw() {
		System.out.print("계좌번호를 입력해주세요.: ");
		BankTest.scanner.nextLine();
		this.setAccount(BankTest.scanner.nextLine());
		
		if(this.checkingAccount(this.getAccount())) {
			System.out.print("출금할 금액을 입력해주세요.: ");
			this.setMoney(BankTest.scanner.nextInt());
			
			info temp = BankTest.map.get(this.getAccount());
			if(temp.getMoney() < this.getMoney()) {
				System.out.println("잔액이 부족합니다.");
				return ;
			}
			
			temp.setMoney(temp.getMoney() - this.getMoney());
			BankTest.map.put(this.getAccount(), temp);
			System.out.println(this.getMoney() + "원이 출금되어 총 잔액은 " +
			temp.getMoney() +"원 입니다.");
		}
	}
}
