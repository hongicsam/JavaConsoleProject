package banking4;

public class HighCreditAccount extends Account{
	private int interest;
	private char creditRating;
	private double additionInterest;
	
	public HighCreditAccount(String accountNumber, String name, int balance, int interest, char creditRating) {
		super(accountNumber, name, balance);
		this.interest = interest;
		this.creditRating = creditRating;
		if(additionInterest=='A') {
			this.additionInterest = ICustomDefine.A;
		}else if(additionInterest=='B') {
			this.additionInterest = ICustomDefine.B;
		}else if(additionInterest=='C') {
			this.additionInterest = ICustomDefine.C;
		}
	}
	
	public int getInterest() {
		return interest;
	}

	public void setInterest(int interest) {
		this.interest = interest;
	}

	public char getCreditRating() {
		return creditRating;
	}

	public void setCreditRating(char creditRating) {
		this.creditRating = creditRating;
	}

	public double getAdditionInterest() {
		return additionInterest;
	}

	public void setAdditionInterest(double additionInterest) {
		this.additionInterest = additionInterest;
	}

	@Override
	public void balancePlus(int num) {
		double transInterest = interest * 0.01;
		setBalance((int) (getBalance()+(getBalance()*(transInterest+additionInterest)))+num);
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.print("기본이자 : " + interest + "%");
		System.out.println(", 신용등급 : " + creditRating);
	}
}
