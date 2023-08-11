package banking4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {
	
	
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		AccountManager manager = new AccountManager();
		while(true) {
			
			manager.showMenu();
			
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
				case MenuChoice.DELETE:
					manager.deleteAccount();
					break;
				case MenuChoice.EXIT:
					System.out.println("프로그램종료");
					return;
				}
				if(0>=choice||choice>=7) {
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}
			} catch (InputMismatchException e) { 
				System.out.println("문자를 입력하셧습니다. 다시 입력해주세요.");
				scan.nextLine();
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
			
		}
	}
}
