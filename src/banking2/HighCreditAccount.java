package banking2;

public class HighCreditAccount extends NormalAccount{
	private char additionInterest;
	
	public HighCreditAccount(String accountNumber, String name, int balance, int interest, char additionInterest) {
		super(accountNumber, name, balance, interest);
		this.additionInterest = additionInterest;
	}
	
	public double getAdditionInterest() {
		return additionInterest;
	}

	public void setAdditionInterest(char additionInterest) {
		this.additionInterest = additionInterest;
	}

	@Override
	public void balancePlus(int num) {
		double transInterest = getInterest() * 0.01;
		double transAdditionInterest = 0;
		if(additionInterest=='A') {
			transAdditionInterest = 0.07;
		}else if(additionInterest=='B') {
			transAdditionInterest = 0.04;
		}else if(additionInterest=='C') {
			transAdditionInterest = 0.02;
		}
		setBalance((int) (getBalance()+(getBalance()*(transInterest+transAdditionInterest)))+num);
	}
	
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("신용등급 : " + additionInterest);
	}
}
