package javaTest.bank;

public class deposit extends info {
	public deposit() {
		System.out.print("계좌번호를 입력해주세요.: ");
		BankTest.scanner.nextLine();
		this.setAccount(BankTest.scanner.nextLine());
		
		if(this.checkingAccount(this.getAccount())) {
			System.out.print("입금할 금액을 입력해주세요.: ");
			this.setMoney(BankTest.scanner.nextInt());
			
			info temp = BankTest.map.get(this.getAccount());
			temp.setMoney(temp.getMoney()+this.getMoney());
			BankTest.map.put(temp.getAccount(), temp);
			
			System.out.println(this.getMoney() + "원이 입금되어 총 잔액은 " + 
			temp.getMoney() +"원 입니다.");
		}
	}
}
