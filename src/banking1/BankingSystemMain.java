package banking1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystemMain {


	static Account[] myAccount = new Account[50];
	static int numOfAccounts = 0;
	
	
	public static void showMenu() {
		System.out.println("-----Menu-----");
		System.out.println("1.계좌개설 ");
		System.out.println("2.입	금");
		System.out.println("3.출	금 ");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료 ");
	}
	
	public static void makeAccount() {
		Scanner scan = new Scanner(System.in);
		
		String iAccountNumber;
		String iName;
		int iBalance;
		
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호 : ");
		iAccountNumber = scan.nextLine();
		System.out.print("고객이름 : ");
		iName = scan.nextLine();
		System.out.print("잔고 : ");
		iBalance = scan.nextInt();
		
		myAccount[numOfAccounts++]= new Account(iAccountNumber, iName, iBalance);
		System.out.println("계좌계설이 완료되었습니다.");
	}
	static void depositMoney() {
		Scanner scan = new Scanner(System.in);
		
		String searchAccount;
		int deposit;
		
		System.out.println("***입   금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		searchAccount = scan.nextLine();
		System.out.print("입금액 : ");
		deposit = scan.nextInt();
		
		for(int i=0; i<numOfAccounts; i++) {
			if(searchAccount.compareTo(myAccount[i].getAccountNumber())==0) {
				myAccount[i].plusAcc(deposit);
			}
		}
		
		System.out.println("입금이 완료되었습니다.");
	}
	static void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		
		String searchAccount;
		int withdraw;
		System.out.println("***출   금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호 : ");
		searchAccount = scan.nextLine();
		System.out.print("출금액 : ");
		withdraw = scan.nextInt();
		
		for(int i=0; i<numOfAccounts; i++) {
			if(searchAccount.compareTo(myAccount[i].getAccountNumber())==0) {
				myAccount[i].minusAcc(withdraw);
			}
		}
		
		System.out.println("출금이 완료되었습니다.");
	}
	public static void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(int i=0; i<numOfAccounts; i++) {
			myAccount[i].showAccInfo();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			showMenu();
			
			try {
				
				System.out.print("선택 : ");
				int choice = scan.nextInt();
				scan.nextLine();
				
				switch(choice) {
				case MenuChoice.MAKE:
					makeAccount();
					break;
				case MenuChoice.DEPOSIT:
					depositMoney();
					break;
				case MenuChoice.WITHDRAW:
					withdrawMoney();
					break;
				case MenuChoice.INQUIRE:
					showAccInfo();
					break;
				case MenuChoice.EXIT:
					System.out.println("프로그램종료");
					return;
				default :
					System.out.println("입력한 값의 범위가 잘못되었습니다.");
					System.out.println("1~5사이의 숫자를 입력해주세요.");
				}
			} catch (InputMismatchException e) {
				System.out.println("입력한 값이 숫자가 아닙니다.");
				System.out.println("1~5사이의 숫자를 입력해주세요.");
				scan.nextLine();
			}
		}
	}
}
