package gamepackage;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;


public class PowerUp extends Sprite {
	public static final int MAX_FISH_SPEED = 5;


	public final static int POWERUP_WIDTH=25;

	// attributes for determining what type it is
	public final static int POWERUP_HP = 1;
	public final static int POWERUP_INVISIBILITY = 2;
	private int type;

	PowerUp(int x, int y, Ship ship,int type){
		super(x,y);

		this.type=type;


	}

	// kapag nagcollide yung ship at powerup, activate powerup
	void checkCollision(Ship ship){
		if(this.collidesWith(ship)){

			this.visible=false;

			// Make use of the powerup.
			if (this.type == POWERUP_HP) {

				ship.usePearlPowerup();
				Media hp = new Media(Paths.get("src/sounds/hp.mp3").toUri().toString());
				MediaPlayer player9 = new MediaPlayer(hp);
				player9.play();
				player9.setAutoPlay(true);

			} else if (this.type == POWERUP_INVISIBILITY) {

				Media shield = new Media(Paths.get("src/sounds/shield.mp3").toUri().toString());
				MediaPlayer player8 = new MediaPlayer(shield);
				player8.play();
				player8.setAutoPlay(true);
				ship.useInvisibility();

			}

		}else{

			Thread waiter = new Thread(new LimitedTimerPower(5, this));
			waiter.start();
		}
	}
}
