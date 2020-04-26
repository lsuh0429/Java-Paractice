package javaTest.bank;

import java.util.*;

public class BankTest {
	public static HashMap<String, info> map = new HashMap<String, info>();
	public static Scanner scanner = new Scanner(System.in);
	public info temp;
	public BankTest() {
		boolean run =true;
		while(run) {
			System.out.println("----------------------------------------------");
			System.out.println("| 1.계좌생성 | 2.입금 | 3.출금 | 4.잔액조회 | 5.계좌삭제 |");
			System.out.println("----------------------------------------------");
			System.out.print("메뉴를 선택해주세요(종료:0) : ");
			int select = scanner.nextInt();
			
			switch(select) {
			case 1:
				temp = new createAccount();
				map.put(temp.getAccount(), temp);
				break;
			case 2:
				new deposit();
				break;
			case 3:
				new withdraw();
				break;
			case 4:
				new balance();
				break;
			case 5:
				new delete();
				break;
			case 0:
				run = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			default :
				System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
			}
		}
	}
   
	public static void main(String[] args) {
		
		
		new BankTest();
		 /*
		*
			문제 : 계좌관리 시스템을 구현하세요.
			※ DB없이 입력 값으로 소모성 데이터로 출력
			※ 입금,출금,잔액조회,계좌생성(랜덤계좌번호),삭제 기능 구현
			※ 나머지 기능 자유 구현
			※ 각각의 기능 객체 지향 프로그래밍 (상속)지향.
		*
		*/
	}

}
