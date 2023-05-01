package gamepackage;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.Random;

public class SmallerEnemy extends Sprite {

	public static final int MAX_BOSS_ENEMY_SPEED = 5;
	public final static Image SMALLER_ENEMY_IMAGE = new Image("images/Animated images/Smaller_Enemy.gif",SmallerEnemy.FISH_WIDTH,SmallerEnemy.FISH_WIDTH,true,false);
	public final static Image SMALLER_ENEMY = new Image("images/Animated images/Smaller_Enemy_dead.gif",SmallerEnemy.FISH_WIDTH,SmallerEnemy.FISH_WIDTH,true,false);
	public final static Image BULLET_IMPACT1 = new Image("images/Animated images/Smaller_Enemy_dead.gif",SmallerEnemy.FISH_WIDTH,SmallerEnemy.FISH_WIDTH,true,false);

	public final static int FISH_WIDTH=85;

	//attribute that will determine if a fish will initially move to the right
	private boolean moveRight;
	private int speed;


	SmallerEnemy(int x, int y){
		super(x,y);
		this.loadImage(SmallerEnemy.SMALLER_ENEMY_IMAGE);

		Random r = new Random();
		this.speed = 2 + r.nextInt(MAX_BOSS_ENEMY_SPEED);

	}

	//method that changes the x position of the fish
	void move(){
		if (moveRight && this.x < (GameStage.WINDOW_WIDTH-FISH_WIDTH)) {
			this.x = this.x + this.speed;
		} else if (moveRight && this.x >= (GameStage.WINDOW_WIDTH-FISH_WIDTH)) {
			this.moveRight = false;
			this.x = this.x - this.speed;
		} else if (!moveRight && this.x > 0) {
			this.x = this.x - this.speed;
		} else if (!moveRight && this.x <= 0) {
			this.moveRight = true;
			this.x = this.x + this.speed;
		}
	}

	// traverses through all the bullets of the ship
	// kapag nagcollide yung bullet at fish, magvavanish yung fish at bullet
	void checkCollision(Ship ship){
		for (int i=0; i< ship.getBullets().size(); i++){
			if(this.collidesWith(ship.getBullets().get(i))){

				ship.getBullets().get(i).boomVisible=true;
				ship.getBullets().get(i).loadBoomImage(SmallerEnemy.BULLET_IMPACT1);       // kapag smaller eneemy ginawa ko nalang na poof na agad pag natamaan ng bullet, bale ipapakita nalang
				ship.getBullets().get(i).setVisible(false);
				Media explode = new Media(Paths.get("src/sounds/explode.mp3").toUri().toString());
				MediaPlayer player2 = new MediaPlayer(explode);
				player2.play();
				player2.setAutoPlay(true);
				this.loadImage(SmallerEnemy.BULLET_IMPACT1);
				this.setVisible(false);

				ship.addScore();
			}
		}

		//kapag naman nagcollide yung fish at ship, marereduce yung strength ng ship
		if(this.collidesWith(ship)){

			// if ship is invisible, add to score
			if (!ship.isInvisibleFromPowerup()) {
				ship.setStrength(30,2);
			} else {
				ship.addScore();
			}

			this.boomVisible=true;
			this.loadBoomImage(BULLET_IMPACT1);
			this.setVisible(false);

		}
	}
}
