package javaTest.bank;

public class delete extends info {
	public delete() {
		System.out.print("계좌번호를 입력해주세요.: ");
		BankTest.scanner.nextLine();
		this.setAccount(BankTest.scanner.nextLine());
		
		if(this.checkingAccount(this.getAccount())) {
			info temp = BankTest.map.get(this.getAccount());
			System.out.print("이름을 입력해주세요 :");
			this.setName(BankTest.scanner.nextLine());
			System.out.print("휴대폰 번호를 입력해주세요(ex.000-0000-0000): ");
			this.setPhone(BankTest.scanner.nextLine());
			
			if(temp.getName().compareTo(this.getName()) == 0 
					&& temp.getPhone().compareTo(this.getPhone())==0) {
				BankTest.map.remove(this.getAccount());
				System.out.println("계좌 정보가 삭제되었습니다.");
			}
			else
				System.out.println("계좌 정보가 일치하지 않습니다. 메뉴 화면으로 돌아갑니다.");
		}
	}
}
