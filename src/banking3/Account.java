package banking3;

abstract class Account {
	
	private String accountNumber;
	private String name;
	private int balance;
	
	public Account() {}
	
	public Account(String accountNumber, String name, int balance) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
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
