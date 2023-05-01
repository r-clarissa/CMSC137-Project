package gamepackage;

import java.lang.Thread;

import javafx.scene.image.ImageView;

public class LimitedTimerBackgroundWelcomeShip implements Runnable {

	private int count;
	private boolean finished=false;
	private boolean first1SecDone=false;
	private boolean second1SecDone=false;
	private boolean third1SecDone=false;

	private ImageView welcomeShip;
	private Thread wait;
	private Thread thisThread = new Thread(this);

	public LimitedTimerBackgroundWelcomeShip(ImageView welcomeShip) {
		this.count = 25;
		this.welcomeShip = welcomeShip;
	}

	@Override
	public void run() {
		System.out.println(this.finished);
		 if (!this.finished) {
			 if (this.third1SecDone) {
					for (int i=0; i<count; i++) {
						this.welcomeShip.setLayoutX(this.welcomeShip.getLayoutX()+2);
						this.welcomeShip.setLayoutY(this.welcomeShip.getLayoutY()+2);

						try {Thread.sleep(30);} catch (InterruptedException e) {}
					}
				} else if (this.second1SecDone) {
					for (int i=0; i<count; i++) {
						this.welcomeShip.setLayoutX(this.welcomeShip.getLayoutX()-2);
						this.welcomeShip.setLayoutY(this.welcomeShip.getLayoutY()-3);

						try {Thread.sleep(22);} catch (InterruptedException e) {}
					}
				} else if (this.first1SecDone) {
					for (int i=0; i<count; i++) {
						this.welcomeShip.setLayoutY(this.welcomeShip.getLayoutY()+3);
						try {Thread.sleep(22);} catch (InterruptedException e) {}
					}
				}

				LimitedTimerWait1SecForShip waiter = new LimitedTimerWait1SecForShip(this);
				this.wait = new Thread(waiter);
				this.wait.start();
		 }

	}

	public void finished1Sec() {
		System.out.println(this.finished);

		if (!this.finished) {
			if (this.third1SecDone == true) {
				this.finished = true;
				this.count=10;
			} else if (this.second1SecDone == true) {
				this.third1SecDone = true;
			} else if (this.first1SecDone == true) {
				this.second1SecDone = true;
			} else if (this.first1SecDone==false) {
				this.first1SecDone = true;
			}
			this.thisThread = new Thread(this);
			this.thisThread.start();
		}
	}

	public Thread getHelperThread() {
		return this.wait;
	}

	public void stopRender() {
		this.finished = true;
		this.thisThread.interrupt();
	}
}

	// a simple runnable object which sleeps until the designated time(seconds)