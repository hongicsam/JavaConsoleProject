package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {

	Account[] myAccount = new Account[50];
	int numOfAccounts = 0;
	
	public void showMenu() {
		System.out.println("====================Menu====================");
		System.out.println("‖ 1.계좌개설          2.입금          3.출금   ‖");
		System.out.println("‖ 4.계좌정보출력  5.프로그램종료                 ‖");
		System.out.println("============================================");
	}
	
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		
		int accountSelect;

		try {
			System.out.println("******신규계좌개설******");
			System.out.println("-------계좌선택-------");
			System.out.println("1.보통계좌 2.신용신뢰계좌");
			System.out.print("선택 : ");
			accountSelect = scan.nextInt();
			scan.nextLine();
			if(accountSelect!=1&&accountSelect!=2) {
				System.out.println("1이나 2를 입력해주세요.");
				return;
			}
			
			String iAccountNumber;
			String iName;
			int iBalance;
			int iInterest;
			String iCreditS;
			char iCreditC;
			
			System.out.print("계좌번호 : ");
			iAccountNumber = scan.nextLine();
			
			System.out.print("고객이름 : ");
			iName = scan.nextLine();
			
			System.out.print("잔고 : ");
			iBalance = scan.nextInt();
			scan.nextLine();

			System.out.print("기본이자%(정수형태로입력) : ");
			iInterest = scan.nextInt();
			scan.nextLine();
			
			if(accountSelect==1) {
				myAccount[numOfAccounts++]= new NormalAccount(iAccountNumber, iName, iBalance, iInterest);
				System.out.println("계좌계설이 완료되었습니다.");
			}else if(accountSelect==2) {
				System.out.print("신용등급(A,B,C등급) : ");
				iCreditS = scan.next();
				iCreditC = iCreditS.charAt(0);
				if((iCreditC!='A')&&(iCreditC!='B')&&(iCreditC!='C')) {
					System.out.println("A, B, C 중에 입력해주세요");
					return;
				}
				
				myAccount[numOfAccounts++]= new HighCreditAccount(iAccountNumber, iName, iBalance, iInterest, iCreditC);
				System.out.println("계좌계설이 완료되었습니다.");
			}
		} catch (InputMismatchException e) {
			System.out.println("문자를 입력하셧습니다. 다시 입력해주세요.");
		}
	}
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		
		String searchAccount;
		int deposit;
		try {
			System.out.println("******입   금******");
			System.out.println("계좌번호와 입금할 금액을 입력하세요.");
			System.out.print("계좌번호 : ");
			searchAccount = scan.nextLine();
			System.out.print("입금액 : ");
			deposit = scan.nextInt();
			if(deposit<=0) {
				System.out.println("0원 이하는 입금할 수 없습니다.");
				return;
			}else if(deposit%500!=0) {
				System.out.println("입금은 500원 단위로만 가능합니다.");
				return;
			}
			
			for(int i=0; i<numOfAccounts; i++) {
				if(searchAccount.compareTo(myAccount[i].getAccountNumber())==0) {
					myAccount[i].balancePlus(deposit);
				}
			}
			System.out.println("입금이 완료되었습니다.");
		} catch (InputMismatchException e) {
			System.out.println("문자를 입력하셧습니다. 다시 입력해주세요.");
		}
	}
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		
		String searchAccount;
		int withdraw;
		try {
			System.out.println("******출   금******");
			System.out.println("계좌번호와 출금할 금액을 입력하세요.");
			System.out.print("계좌번호 : ");
			searchAccount = scan.nextLine();
			System.out.print("출금액 : ");
			withdraw = scan.nextInt();
			if(withdraw<=0) {
				System.out.println("0원 이하는 출금할 수 없습니다.");
				return;
			}else if(withdraw%1000!=0) {
				System.out.println("출금은 1000원 단위로만 가능합니다.");
				return;
			}
			
			for(int i=0; i<numOfAccounts; i++) {
				if(searchAccount.compareTo(myAccount[i].getAccountNumber())==0) {
					if(myAccount[i].getBalance()<withdraw){
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					}
					myAccount[i].balanceMinus(withdraw);
				}
			}
			System.out.println("출금이 완료되었습니다.");
		} catch (InputMismatchException e) {
			System.out.println("문자를 입력하셧습니다. 다시 입력해주세요.");
		}
	}
	public void showAccInfo() {
		System.out.println("***************계좌정보출력***************");
		for(int i=0; i<numOfAccounts; i++) {
			System.out.println("--------------------------");
			myAccount[i].showAccInfo();
			System.out.println("\n--------------------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

}
