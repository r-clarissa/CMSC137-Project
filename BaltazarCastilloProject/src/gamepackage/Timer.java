package gamepackage;

import java.text.DecimalFormat;

public class Timer implements Runnable {

	// time left
	private int mins=1;
	private int sec=0;
	private boolean continueCounter = true;	// for the while loop
	private boolean isTimerFinished = false;
	private String stringedTime = getSecondsLeft();

	// for formatting the seconds variable when printed
	private DecimalFormat decimalFormat;


	public void run() {

		// Just to make sure that the mins and sec above is displayed properly
		this.sec = this.sec+1;

		// while continueCounter is TRUE, do while loop; becomes FALSE after time is left.
		while (this.continueCounter) {

			// Time check: When there is 0 seconds
			// left but there is still mins left
			if (this.sec == 0 && this.mins > 0) {
				this.mins = this.mins-1;
				this.sec = 60;	// add back 60 to seconds, decrement mins
			}

			// decrement seconds variable and increments seconds counter
			this.sec = this.sec-1;

			// makes sure that one digit sec values have 0 beside it (e.g. 9 -> 09)
			this.decimalFormat = new DecimalFormat("00");
			String formattedSec = decimalFormat.format(sec);

			this.stringedTime = mins + ":" + formattedSec;



			// if no more time left, stop while loop.
			if ((this.mins==0) && (this.sec == 0)) {
				this.continueCounter = false;
				this.stringedTime = "0:00";
			}

			// 1 second interval
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}

		}

		// TEMPORARY: prints done. [When we're done with other classes: CALL END SCREEN]
		this.isTimerFinished = true;

	}

	// Ends the timer
	void endTimer() {
		this.continueCounter = false;
	}

	// Getters
	String getSecondsLeft() {
		String time = this.stringedTime;
		if (this.stringedTime == null) {
			decimalFormat = new DecimalFormat("00");
			time = mins + ":" + decimalFormat.format(sec);
		}

		return (time);
	}

	boolean isFinished() {
		return this.isTimerFinished;
	}
}
