package banking5;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	Scanner scan = new Scanner(System.in);
	HashSet<Account> accountSet = new HashSet<Account>();
	
	String searchAccount;
	
	public void outPutAccount() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/banking5/AccountInfo.obj"));
			for(Account ac: accountSet) {
				out.writeObject(ac);
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void inPutAccount() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/banking5/AccountInfo.obj"));
			while(true) {
				accountSet.add((Account)in.readObject());
			}
//			in.close(); // 루프중에 catch절로 넘어가기때문에 실행 안됨
		} catch (FileNotFoundException e) {
			System.out.println("저장된 파일을 찾을 수 없습니다.");
		} catch (EOFException e) { // End Of File Exception 파일이 끝났음을 알리는 예외
			System.out.println("저장된 파일을 불러왔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			in.close(); // catch후에 반드시 실행되긴 하지만 결국 try문 바깥이라서 정의가 안된상태임	결론 in은 못닫는다.
		}
	}
	
	public void showMenu() {
		System.out.println("====================Menu====================");
		System.out.println("‖ 1.계좌개설     2.입금        3.출금          ‖");
		System.out.println("‖ 4.계좌정보출력  5.계좌정보삭제  6.프로그램종료    ‖");
		System.out.println("============================================");
	}
	
	public void makeAccount() {
		int accountSelect;

		try {
			System.out.println("***************신규계좌개설***************");
			System.out.println("-----------------계좌선택----------------");
			System.out.println("        1.보통계좌        2.신용신뢰계좌    ");
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
			boolean duplication = true;
			String yn;
			
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
				Account newAccount = new NormalAccount(iAccountNumber, iName, iBalance, iInterest);
				duplication = accountSet.add(newAccount);
				if(!duplication) {
					System.out.println("중복된 계좌번호가 발견되었습니다.");
					System.out.print("덮어쓰시겠습니까? y or n : ");
					yn = scan.nextLine();
					if(yn.equals("y")) {
						for(Account ac: accountSet) {
							if(iAccountNumber.equals(ac.getAccountNumber())) {
								accountSet.remove(ac);
							}
						}
						accountSet.add(newAccount);
						System.out.println("덮어쓰기를 하였습니다.");
						System.out.println("계좌계설이 완료되었습니다.");
					}else if(yn.equals("n")) {
						System.out.println("계좌개설을 취소하였습니다.");
						return;
					}else {
						System.out.println("y 혹은 n을 입력해주세요.");
					}
				}else {
					System.out.println("계좌계설이 완료되었습니다.");
				}
			}else if(accountSelect==2) {
				System.out.print("신용등급(A,B,C등급) : ");
				iCreditS = scan.nextLine();
				iCreditC = iCreditS.charAt(0);
				if((iCreditC!='A')&&(iCreditC!='B')&&(iCreditC!='C')) {
					System.out.println("A, B, C 중에 입력해주세요");
					return;
				}
				Account newAccount= new HighCreditAccount(iAccountNumber, iName, iBalance, iInterest, iCreditC);
				duplication = accountSet.add(newAccount);
				if(!duplication) {
					System.out.println("중복된 계좌번호가 발견되었습니다.");
					System.out.print("덮어쓰시겠습니까? y or n : ");
					yn = scan.nextLine();
					if(yn.equals("y")) {
						for(Account ac: accountSet) {
							if(iAccountNumber.equals(ac.getAccountNumber())) {
								accountSet.remove(ac);
							}
						}
						accountSet.add(newAccount);
						System.out.println("덮어쓰기를 하였습니다.");
						System.out.println("계좌계설이 완료되었습니다.");
					}else if(yn.equals("n")) {
						System.out.println("계좌개설을 취소하였습니다.");
						return;
					}else {
						System.out.println("y 혹은 n을 입력해주세요.");
					}
				}else {
					System.out.println("계좌계설이 완료되었습니다.");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("문자를 입력하셧습니다. 다시 입력해주세요.");
			scan.nextLine();
		}
	}
	
	public void depositMoney() {
		int deposit;
		
		try {
			System.out.println("***************입   금***************");
			System.out.println("계좌번호와 입금할 금액을 입력하세요.");
			System.out.print("계좌번호 : ");
			searchAccount = scan.nextLine();
			System.out.print("입금액 : ");
			deposit = scan.nextInt();
			scan.nextLine();
			if(deposit<=0) {
				System.out.println("0원 이하는 입금할 수 없습니다.");
				return;
			}else if(deposit%500!=0) {
				System.out.println("입금은 500원 단위로만 가능합니다.");
				return;
			}
			for(Account ac: accountSet) {
				if(searchAccount.equals(ac.getAccountNumber())) {
					ac.balancePlus(deposit);
				}
			}
			System.out.println("입금이 완료되었습니다.");
		} catch (InputMismatchException e) {
			System.out.println("문자를 입력하셧습니다. 다시 입력해주세요.");
			scan.nextLine();
		}
	}
	
	public void withdrawMoney() {
		int withdraw;
		String yesno;
		
		try {
			System.out.println("***************출   금***************");
			System.out.println("계좌번호와 출금할 금액을 입력하세요.");
			System.out.print("계좌번호 : ");
			searchAccount = scan.nextLine();
			System.out.print("출금액 : ");
			withdraw = scan.nextInt();
			scan.nextLine();
			
			if(withdraw<=0) {
				System.out.println("0원 이하는 출금할 수 없습니다.");
				return;
			}else if(withdraw%1000!=0) {
				System.out.println("출금은 1000원 단위로만 가능합니다.");
				return;
			}
			
			for(Account ac: accountSet) {
				if(searchAccount.equals(ac.getAccountNumber())) {
					if(ac.getBalance()<withdraw) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("YES : 금액전체 출금처리, NO : 출금요청취소");
						System.out.print("YES or NO : ");
						yesno = scan.nextLine();
						
						if(yesno.equals("YES")) {
							System.out.println("금액전체를 출금합니다.");
							ac.balanceMinus(ac.getBalance());
						}else if(yesno.equals("NO")) {
							System.out.println("출금요청을 취소합니다.");
							return;
						}else {
							System.out.println("YES 혹은 NO를 입력해주세요.");
							return;
						}
					}else {
						ac.balanceMinus(withdraw);
						System.out.println("출금이 완료되었습니다.");
					}
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("문자를 입력하셧습니다. 다시 입력해주세요.");
			scan.nextLine();
		}
	}
	
	public void showAccInfo() {
		System.out.println("***************계좌정보출력***************");
		for(Account ac: accountSet) {
			System.out.println("--------------------------");
			ac.showAccInfo();
			System.out.println("--------------------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	public void deleteAccount() {
		System.out.println("***************계좌정보삭제***************");
		System.out.print("계좌번호 : ");
		searchAccount = scan.nextLine();
		
		for(Account ac: accountSet) {
			if(searchAccount.equals(ac.getAccountNumber())) {
				accountSet.remove(ac);
				System.out.println("계좌정보가 삭제되었습니다.");
			} else {
				System.out.println("입력하신 계좌번호가 존재하지 않습니다.");
			}
		}
	}
}
