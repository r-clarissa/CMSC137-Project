package gamepackage;

import java.lang.Thread;

public class LimitedTimer implements Runnable {

	private int timeLimit;
	private Ship ship;

	public LimitedTimer(int sec, Ship ship) {
		this.timeLimit = sec;
		this.ship = ship;
	}

	@Override
	public void run() {

		for (int i=0; i<timeLimit; i++) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {};
		}

		this.ship.invisibilityDone();

	}
}
