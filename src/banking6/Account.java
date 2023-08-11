package banking6;

import java.io.Serializable;

abstract class Account implements Serializable {
	
	private String accountNumber;
	private String name;
	private int balance;
	
	public Account() {}
	
	public Account(String accountNumber, String name, int balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
	}
	
	@Override
	abstract public String toString();

	@Override
	public int hashCode() {
		int returnCode = getAccountNumber().hashCode();
		return returnCode;
	}
	@Override
	public boolean equals(Object obj) {
		Account account = (Account)obj;
		if((account.accountNumber.equals(this.accountNumber))) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void balancePlus(int num) {
		balance += num;
	}
	
	public void balanceMinus(int num) {
		balance -= num;
	}

	public void showAccInfo() {
		System.out.println("계좌번호 : " + accountNumber + ", 고객이름 : " + name);
		System.out.println("잔고 : " + balance);
	}
}
