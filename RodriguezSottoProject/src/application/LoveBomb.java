package application;

import javafx.scene.image.Image;

public class LoveBomb extends PowerUps{
	public final static Image LOVE_BOMB_IMAGE = new Image("images/power1.gif",PowerUps.POWERUP_WIDTH,PowerUps.POWERUP_HEIGHT,false,false);

	LoveBomb(int x, int y) {
		super(x, y, LoveBomb.LOVE_BOMB_IMAGE);
	}


	/* method that will check if the love bomb power up collides with the shooter, set its visibility
	 * to false by calling the despawn method from the PowerUps class if it does collide with the shooter,
	 * and make the shooter invincible for a certain amount of time by calling the makeInvincible method from
	 * the Shooter class
	 */
	void checkCollision(Shooter shooter) {
		if(this.collidesWith(shooter)) {
			this.despawn();
			shooter.makeInvincible();
		}
	}
}
