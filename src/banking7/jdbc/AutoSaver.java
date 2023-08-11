package banking7.jdbc;


public class AutoSaver extends Thread {
	private AccountManager am;
	
	public AutoSaver(AccountManager manager) {
		am = manager;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				am.autoSave();
				System.out.println("\n자동저장이 실행중입니다.");
				sleep(5000);
			}
		} catch (InterruptedException e) {
			System.out.println("\n자동저장이 종료되었습니다.");
		}
	}
	
	
}
