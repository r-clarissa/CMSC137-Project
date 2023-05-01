package application;

import javafx.scene.image.Image;

public abstract class PowerUps extends Sprite {
	public final static int POWERUP_WIDTH = 27;
	public final static int POWERUP_HEIGHT = 27;
	public static final int POWERUP_DESPAWN_TIME = 5;
	private int availTimeElapsed;

	protected PowerUps(int x, int y, Image img){
		super(x,y,img);
		this.availTimeElapsed = -1; ///initially was zero
	}

	//method that will check if the power up can still be collected
	boolean isAvailable() {
		return this.getVisible();
	}

	/*method that will set the elapsed time of the power up and visibility of the power up to false if
	 the elapsed time is equal to the despawn time*/
	void setAvailabilityTimeElapsed() {
		this.availTimeElapsed += 1;
		if(this.availTimeElapsed == POWERUP_DESPAWN_TIME)
			this.despawn();
	}

	void despawn() {
		this.setVisible(false);
	}

	abstract void checkCollision(Shooter shooter);
}
