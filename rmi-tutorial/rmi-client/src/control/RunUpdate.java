package control;

import view.MainScreen;

public class RunUpdate implements Runnable{

	private MainScreen mainScreen;
	
	public RunUpdate(MainScreen mainScreen) {
		super();
		this.mainScreen = mainScreen;
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (!mainScreen.isTwitter()) {
					Thread.sleep(30000);
				}else{
					Thread.sleep(60000);
				}
				
				mainScreen.updateUser();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
