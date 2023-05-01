package gamepackage;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;
import java.util.Random;

public class BossEnemy extends Sprite {

	public static final int MAX_ENEMY_SPEED = 5;
	public static final int HEALTH_BOSS= 3000;
	public final static int BOSS_WIDTH=200;
	public final static Image BOSS_IMAGE = new Image("images/Animated images/Boss.gif",BossEnemy.BOSS_WIDTH,BossEnemy.BOSS_WIDTH,true,false);
	public final static Image BOSS_DIE = new Image("images/Non-animated images/Shoot_Animation_1.png",BossEnemy.BOSS_WIDTH,BossEnemy.BOSS_WIDTH,true,false);

	private int health;
	private boolean moveRight;
	private int speed;


	BossEnemy(int x, int y){
		super(x,y);

		this.health=BossEnemy.HEALTH_BOSS;
		this.loadImage(BossEnemy.BOSS_IMAGE);
		this.hasHP=true;

		Random r = new Random();
		this.speed = 2 + r.nextInt(MAX_ENEMY_SPEED);

	}

	//method that changes the x position of the BOSS
	void move(){

		if (moveRight && this.x < (GameStage.WINDOW_WIDTH-BOSS_WIDTH)) {
			this.x = this.x + this.speed;
		} else if (moveRight && this.x >= (GameStage.WINDOW_WIDTH-BOSS_WIDTH)) {
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
	// kapag nagcollide yung bullet at boss mababawasan health ng boss and if <= 0 na yung health is magiging mamamtay na yung boss
	void checkCollision(Ship ship){
		for (int i=0; i< ship.getBullets().size(); i++){
			if(this.collidesWith(ship.getBullets().get(i))){

				int temp= this.health-ship.getStrength();
				this.health=temp;
				Random r= new Random();
				int index= r.nextInt(4);

				ship.getBullets().get(i).boomVisible=true;
				if(index==1){
					ship.getBullets().get(i).loadBoomImage(Bullet.BULLET_IMPACT1);  //magloaload ng baam image sa last position ng bullet before magvanish
				}else if(index==2){
					ship.getBullets().get(i).loadBoomImage(Bullet.BULLET_IMPACT2);
				}else if(index==3){
					ship.getBullets().get(i).loadBoomImage(Bullet.BULLET_IMPACT3);
				}else if(index==4){
					ship.getBullets().get(i).loadBoomImage(Bullet.BULLET_IMPACT4);
				}
				Media bossHit = new Media(Paths.get("src/sounds/bossHit.mp3").toUri().toString());
				MediaPlayer player4 = new MediaPlayer(bossHit);
				player4.play();
				player4.setAutoPlay(true);

				ship.getBullets().get(i).setVisible(false);

				if(this.health<1){
					this.health = 0;
					this.boomVisible = true;
					Media explodeBoss = new Media(Paths.get("src/sounds/explode.mp3").toUri().toString());
					MediaPlayer player3 = new MediaPlayer(explodeBoss);
					player3.play();
					player3.setAutoPlay(true);
					this.loadBoomImage(BOSS_DIE);   //magloaload ng image na poof sa last position ng boss bago madestroy
					this.setVisible(false);
				}
			}
		}

		//kapag naman nagcollide yung Enemy at ship, marereduce yung strength ng ship
		if(this.collidesWith(ship)){

			ship.setStrength(50,1);
			Media shipHit = new Media(Paths.get("src/sounds/shipHit.mp3").toUri().toString());
			MediaPlayer player6 = new MediaPlayer(shipHit);
			player6.play();
			player6.setAutoPlay(true);

		}

	}
}
