package banking2;

import java.util.Scanner;

public class BankingSystemMain {
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설 ");
		System.out.println("2.입	금");
		System.out.println("3.출	금 ");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료 ");
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		AccountManager manager = new AccountManager();
		while(true) {
			
			showMenu();
			
			try {
				System.out.print("선택 : ");
				int choice = scan.nextInt();
				scan.nextLine();
			
				switch(choice) {
				case MenuChoice.MAKE:
					manager.makeAccount();
					break;
				case MenuChoice.DEPOSIT:
					manager.depositMoney();
					break;
				case MenuChoice.WITHDRAW:
					manager.withdrawMoney();
					break;
				case MenuChoice.INQUIRE:
					manager.showAccInfo();
					break;
				case MenuChoice.EXIT:
					System.out.println("프로그램종료");
					return;
				default :
					System.out.println("입력한 값의 범위가 잘못되었습니다.");
					System.out.println("1~5사이의 숫자를 입력해주세요.");
			}
			} catch (Exception e) { // 예외처리는 나중에 모아서 처리할 예정
				e.printStackTrace();
			}
			
		}
	}
}
