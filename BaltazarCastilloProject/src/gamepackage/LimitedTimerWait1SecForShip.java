package gamepackage;

import java.lang.Thread;

public class LimitedTimerWait1SecForShip implements Runnable {

	private LimitedTimerBackgroundWelcomeShip previousTimer;

	public LimitedTimerWait1SecForShip(LimitedTimerBackgroundWelcomeShip previousTimer) {
		this.previousTimer = previousTimer;
	}

	@Override
	public void run() {
		try {Thread.sleep(1000);} catch (InterruptedException e) {};
		previousTimer.finished1Sec();
	}
}

	// a simple runnable object which sleeps until the designated time(seconds)