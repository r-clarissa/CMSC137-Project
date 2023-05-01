package application;

import javafx.scene.image.Image;

public class EnergyDrink extends PowerUps{
	public final static Image ENERGY_DRINK_IMAGE = new Image("images/power2.gif",PowerUps.POWERUP_WIDTH,PowerUps.POWERUP_HEIGHT,false,false);

	EnergyDrink(int x, int y) {
		super(x, y, EnergyDrink.ENERGY_DRINK_IMAGE);
	}

	/*method that will check if the power up collides with shooter and set the visibility to false if
	 it does collide with the shooter*/
	void checkCollision(Shooter shooter) {
		if(this.collidesWith(shooter)) {
			this.despawn();
			shooter.increaseStrength();
		}
	}
}
