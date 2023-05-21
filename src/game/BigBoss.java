package game;

import javafx.scene.image.Image;
import stages.GameStage;
import stages.SuperStage;

public class BigBoss extends Sprite {

	private boolean alive;
	private boolean moveDown;
	private boolean diagonal = false;
	private double speed;
	private int life;
	public int DAMAGE_STRENGTH;
	private GameTimer gamerTimer;

	BigBoss(GameTimer gamerTimer) {
		super(1200,700);
		// TODO Auto-generated constructor stub
		this.alive = true;
		this.loadImage(SuperStage.boss1);
		//this.speed ;
		this.life = 3000;
		this.DAMAGE_STRENGTH = 50;
		this.gamerTimer = gamerTimer;
	}

	BigBoss(GameTimer gamerTimer, int bossType) {
		super(1200,700);
		// TODO Auto-generated constructor stub
		this.alive = true;
		this.loadImage(SuperStage.getBossImg(bossType));
		//this.speed ;
		this.life = 3000;
		this.DAMAGE_STRENGTH = 50;
		this.gamerTimer = gamerTimer;
	}

	void move(){
		//Random r = new Random();
		this.speed = 1;// + r.nextInt(2);

		if(this.moveDown == true){ // If moveDown is true and
			if(this.getY() < (SuperStage.WINDOW_HEIGHT-190)){ // if the boss hasn't reached the right boundary yet
				this.y += this.speed; // move the boss to the up by changing the x position of the fish depending on its speed
				moveLeftRight();

			}else if(this.getY() >= (SuperStage.WINDOW_HEIGHT-190)){ // else if it has reached the boundary, change the moveDown value / move up
				this.moveDown = false;
			}
		}else if (this.moveDown == false){ // Else, if moveDown is false and
			if(this.getY() > 60){ // if the fish hasn't reached the upper boundary yet,
				this.y -= this.speed; //move the fish to the down by changing the x position of the fish depending on its speed.
				moveLeftRight();
			}else if(this.getY() <= 60){ // else if it has reached the boundary, change the moveDown value / move down
				this.moveDown = true;
			}
		}
	}


	private void moveLeftRight() {

		if(this.diagonal == true){ // If diagonal is true and
			if(this.getX() < (SuperStage.WINDOW_WIDTH-190)){ // if the fish hasn't reached the right boundary yet
				this.x += this.speed; // move the fish to the right by changing the x position of the fish depending on its speed
			}else if(this.getX() >= (SuperStage.WINDOW_WIDTH-190)){ // else if it has reached the boundary, change the moveRight value / move to the left
				this.diagonal = false;
			}
		} else if(this.diagonal == false){ // Else, if diagonal is false and
			if(this.getX() > 10){ // if the fish hasn't reached the left boundary yet,
				this.x -= this.speed; //move the fish to the left by changing the x position of the fish depending on its speed.
			}else if(this.getX() <= 10){ // else if it has reached the boundary, change the moveRight value / move to the right
				this.diagonal = true;
			}
		}
	}

	// This function is called to show life below the boss
	void showBossLife() {
		Integer life = new Integer(this.life);
		String lifeString = "LIFE: " + life.toString();

		gamerTimer.showText(this.getX()+5, this.getY()+160, lifeString);
	}

	boolean isAlive(){
		return this.alive;
	}

	void damagePlayer(Player player){
		player.deductStrength(this.DAMAGE_STRENGTH);
	}

	void deductBossLife (int playerStrength) {
		this.life -= playerStrength;
	}

	public void die(){
    	this.alive = false;
    }

	int getLife() {
		return this.life;
	}

}
