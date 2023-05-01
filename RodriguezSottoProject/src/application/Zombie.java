package application;

import java.util.Random;
import javafx.scene.image.Image;

public class Zombie extends Sprite {

	private boolean alive;
	private boolean moveRight;
	private int speed;
	private int type; // mini zombie or boss zombie
	private int life; // boss's life

	public static final int MAX_ZOMBIE_SPEED = 5;

	//mini zombie
	public static final  int ZOMBIE_HEIGHT=50;
	public static final  int ZOMBIE_WIDTH=45;
	public final static Image ZOMBIE_EXPLOSION = new Image("images/explosion1.gif",Zombie.ZOMBIE_HEIGHT, Zombie.ZOMBIE_HEIGHT,false,false);
	public final static Image ZOMBIE_RIGHT = new Image("images/zombie-right.gif",Zombie.ZOMBIE_WIDTH, Zombie.ZOMBIE_HEIGHT,false,false);
	public final static Image ZOMBIE_LEFT = new Image("images/zombie-left.gif",Zombie.ZOMBIE_WIDTH, Zombie.ZOMBIE_HEIGHT,false,false);

	//boss zombie
	public final static int BOSS_ZOMBIE_SIZE = 150;
	public final static Image BOSS_ZOMBIE_LEFT = new Image("images/bossz1f-l.gif",Zombie.BOSS_ZOMBIE_SIZE,Zombie.BOSS_ZOMBIE_SIZE,false,false);
	public final static Image BOSS_ZOMBIE_RIGHT = new Image("images/bossz1f-r.gif",Zombie.BOSS_ZOMBIE_SIZE, Zombie.BOSS_ZOMBIE_SIZE, false,false);
	public final static int BOSS_ZOMBIE_LIFE = 3000;


	Zombie(int x, int y, int type){
		super(x,y, type==0?Zombie.ZOMBIE_LEFT:Zombie.BOSS_ZOMBIE_LEFT); // mini or boss
		this.alive = true;
		this.moveRight = false;
		Random r = new Random();
		this.speed = r.nextInt(Zombie.MAX_ZOMBIE_SPEED)+1;
		this.type = type;
		if(type == 1) {
			this.life = Zombie.BOSS_ZOMBIE_LIFE;
		}
	}

	/* method that will move the zombies by changing the position of the it depending on its speed
	 * and will load the image of zombie if it is facing to the left or right
	 */
	void move(){
		if(this.moveRight == true && this.x+this.speed<=GameStage.WINDOW_WIDTH-this.width){
			this.x += this.speed;
		} else if(this.x+this.speed>=GameStage.WINDOW_WIDTH-this.width){
			this.moveRight = false;
			if (this.type == 1)
				this.loadImage(Zombie.BOSS_ZOMBIE_LEFT);
			else
				this.loadImage(Zombie.ZOMBIE_LEFT);
			this.x -= this.speed;
		} else if(this.moveRight == false && this.x-this.speed>=0){
			this.x -= this.speed;
		} else {
			this.moveRight = true;
			if (this.type == 1)
				this.loadImage(Zombie.BOSS_ZOMBIE_RIGHT);
			else
				this.loadImage(Zombie.ZOMBIE_RIGHT);
		}
	}

	//method that will return a boolean value indicating if the shooter is still alive
	boolean isAlive() {
		return this.alive;
	}

	//method that will set the boolean attribute alive to false and change the image of the zombie
	private void die() {
		this.alive = false;
		this.loadImage(Zombie.ZOMBIE_EXPLOSION);
	}

	//method that will return the zombie type
	int zombieType() {
		return this.type;
	}

	//method the will return the hp of the boss zombie
	int getBossLife() {
		return this.life;
	}

	//method that will decrease the hp of the boss zombie
	private void decreaseBossZombieLife(int damage) {
		this.life-=damage;
	}

	/* method that will check whether there is a collision between the zombies and the shooter's bullet or
	 * the zombie and the shooter
	 */
	void checkCollision(Shooter shooter, int type) {
		for(int i=0; i<shooter.getBullets().size(); i++) {
			if(this.collidesWith(shooter.getBullets().get(i))) {
				if(type == 0) { // mini zombie
					this.die();
					shooter.setScore(); // will automatically add score
				} else {
					System.out.println("Boss Zombie Life: " + this.getBossLife());
					this.decreaseBossZombieLife(shooter.getStrength()); // will decrease the boss zombie's hp depending on the shooter's strength
					if(this.getBossLife() <= 0) {
						this.die();
						shooter.setScore(); // will automatically add score
					}
					System.out.println("Boss Zombie Life: " + this.getBossLife());
				}
				shooter.getBullets().get(i).setVisible(false); //will set the visibility of the bullet when it hits the zombie
			}
		}
		if(this.collidesWith(shooter) && !shooter.isInvincible()) {
			if(type == 0) { // mini zombie
				this.die();
			}
			shooter.decreaseStrength(type); // will decrease strength of the shooter depending on the zombie type (mini-0 or boss-1)
		}
	}
}
