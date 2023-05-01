package gamepackage;

import java.lang.Thread;

public class LimitedTimerPower implements Runnable {

	private int timeLimit;
	private PowerUp power;

	public LimitedTimerPower(int sec, PowerUp power) {
		this.timeLimit = sec;
		this.power=power;

	}

	@Override
	public void run() {

		for (int i=0; i<timeLimit; i++) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {};
		}

		this.power.setVisible(false);

	}
}

	// a simple runnable object which sleeps until the designated time(seconds)