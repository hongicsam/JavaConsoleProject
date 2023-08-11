package banking4;

public class NormalAccount extends Account {
	private int interest;

	public NormalAccount(String accountNumber, String name, int balance, int interest) {
		super(accountNumber, name, balance);
		this.interest = interest;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	@Override
	public void balancePlus(int num) {
		double transInterest = interest * 0.01;
		setBalance((int) (getBalance()+(getBalance()*transInterest))+num);
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("기본이자 : " + interest + "%");
	}
}
