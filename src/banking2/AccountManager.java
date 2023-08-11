package banking2;

import java.util.Scanner;

public class AccountManager {

	Account[] myAccount = new Account[50];
	int numOfAccounts = 0;
	
	
	
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		
		int accountSelect;
		
		try {
			System.out.println("***신규계좌개설***");
			System.out.println("-----계좌선택-----");
			System.out.println("1.보통계좌");
			System.out.println("2.신용신뢰계좌");
			System.out.print("선택 : ");
			accountSelect = scan.nextInt();
			scan.nextLine();
			
			String iAccountNumber;
			String iName;
			int iBalance;
			int iInterest;
			String iCreditS;
			char iCreditC;
			
			if(accountSelect==1) {
				System.out.print("계좌번호 : ");
				iAccountNumber = scan.nextLine();
				System.out.print("고객이름 : ");
				iName = scan.nextLine();
				System.out.print("잔고 : ");
				iBalance = scan.nextInt();
				System.out.print("기본이자%(정수형태로입력) : ");
				iInterest = scan.nextInt();
				
				myAccount[numOfAccounts++]= new NormalAccount(iAccountNumber, iName, iBalance, iInterest);
				System.out.println("계좌계설이 완료되었습니다.");
			}else if(accountSelect==2) {
				System.out.print("계좌번호 : ");
				iAccountNumber = scan.nextLine();
				System.out.print("고객이름 : ");
				iName = scan.nextLine();
				System.out.print("잔고 : ");
				iBalance = scan.nextInt();
				System.out.print("기본이자%(정수형태로입력) : ");
				iInterest = scan.nextInt();
				System.out.print("신용등급(A,B,C등급) : ");
				iCreditS = scan.next();
				iCreditC = iCreditS.charAt(0);
				
				
				myAccount[numOfAccounts++]= new HighCreditAccount(iAccountNumber, iName, iBalance, iInterest, iCreditC);
			}
		} catch (Exception e) { // 예외처리는 나중에 모아서 처리할 예정
			e.printStackTrace();
		}
		
		System.out.println("계좌계설이 완료되었습니다.");
	}
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		
		String searchAccount;
		int deposit;
		
		try {
			System.out.println("***입   금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요.");
			System.out.print("계좌번호 : ");
			searchAccount = scan.nextLine();
			System.out.print("입금액 : ");
			deposit = scan.nextInt();
			
			for(int i=0; i<numOfAccounts; i++) {
				if(searchAccount.compareTo(myAccount[i].getAccountNumber())==0) {
					myAccount[i].balancePlus(deposit);
				}
			}
		} catch (Exception e) { // 예외처리는 나중에 모아서 처리할 예정
			e.printStackTrace();
		}

		System.out.println("입금이 완료되었습니다.");
	}
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		
		String searchAccount;
		int withdraw;
		
		try {
			System.out.println("***출   금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요.");
			System.out.print("계좌번호 : ");
			searchAccount = scan.nextLine();
			System.out.print("출금액 : ");
			withdraw = scan.nextInt();
			
			for(int i=0; i<numOfAccounts; i++) {
				if(searchAccount.compareTo(myAccount[i].getAccountNumber())==0) {
					myAccount[i].balanceMinus(withdraw);
				}
			}
		} catch (Exception e) { // 예외처리는 나중에 모아서 처리할 예정
			e.printStackTrace();
		}

		System.out.println("출금이 완료되었습니다.");
	}
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(int i=0; i<numOfAccounts; i++) {
			System.out.println("-------------");
			myAccount[i].showAccInfo();
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

}
