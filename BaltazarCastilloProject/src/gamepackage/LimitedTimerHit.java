package gamepackage;

import java.lang.Thread;

public class LimitedTimerHit implements Runnable {

	private int timeLimit;
	private Ship ship;

	public LimitedTimerHit(int sec, Ship myship) {
		this.timeLimit = sec;
		this.ship=myship;
	}

	@Override
	public void run() {

		for (int i=0; i<timeLimit; i++) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {};
		}

		this.ship.loadImage(Ship.SHIP_IMAGE);

	}
}