package banking6;


public class AutoSaver extends Thread {
	private AccountManager am;
	
	public AutoSaver(AccountManager manager) {
		am = manager;
	}
	
	@Override
	public void run() {
		System.out.println("\n자동저장이 실행되었습니다.");
		try {
			while (true) {
				am.autoSave();
				sleep(5000);
				System.out.println("\n자동저장이 실행중입니다.");
			}
		} catch (InterruptedException e) {
			System.out.println("\n자동저장이 종료되었습니다.");
		}
	}
	
	
}
