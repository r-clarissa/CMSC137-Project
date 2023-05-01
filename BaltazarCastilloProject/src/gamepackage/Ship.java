package gamepackage;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.lang.Thread;
import java.nio.file.Paths;


public class Ship extends Sprite{

	public final static Image SHIP_IMAGE = new Image("images/Animated images/Main_Rocket.gif",Ship.SHIP_WIDTH,Ship.SHIP_WIDTH,true,false);
	public final static Image INVISIBLE_SHIP= new Image("images/Animated images/Main_rocket_invisible.gif",Ship.SHIP_WIDTH,Ship.SHIP_WIDTH,true,false);
	public final static Image INVISIBLE_SHIP_TEMPORARY = new Image("images/Animated images/Main_rocket_invisible_temporary.gif",Ship.SHIP_WIDTH,Ship.SHIP_WIDTH,true,false);
	public final static Image SHIP_HIT = new Image("images/Animated images/shipHit.gif",Ship.SHIP_WIDTH,Ship.SHIP_WIDTH,true,false);

	private int strength;
	private boolean alive;
	private int score=0;	// score count
	private ArrayList<Bullet> bullets;
	private boolean isInvisibleFromPowerup=false;
	private final static int SHIP_WIDTH = 120;

	private boolean powerupInvisibilityActivated = false; // if true, activate invisibility
	public final static int SHIP_MAX_SPEED = 10;

	// For improving functionality of controls.
	private int[] directionArray;	//  FORMTAT: [left, right, down, up]
									//  Add up left and right, updates dx; add up down and up, updates dy

	public Ship(String name, int x, int y){
		super(x,y);

		// This is important for the collide function in Sprite: Disables multiple killing of enemies
		this.hasHP = true;

		// strength must be 100-150
		Random r = new Random();
		this.strength = r.nextInt(50)+100;
		this.alive = true;
		this.bullets = new ArrayList<Bullet>();

		// load image
		this.loadImage(Ship.SHIP_IMAGE);

		// for improved control functionality
		this.directionArray = new int[4];
	}

	boolean isAlive(){
		if(this.alive) return true;
		return false;
	}

	public void die(){
    	this.alive = false;
    }

	//method that will get the bullets 'shot' by the ship
	public ArrayList<Bullet> getBullets(){
		return this.bullets;
	}

	//method called if spacebar is pressed
	public void shoot(){

		//compute for the x and y initial position of the bullet
		Media shoot = new Media(Paths.get("src/sounds/shoot.mp3").toUri().toString());
		MediaPlayer player1 = new MediaPlayer(shoot);
		player1.play();
		player1.setAutoPlay(true);
		int x = (int) (this.x + this.width - 80);
		int y = (int) (this.y + this.height/2 + 7);

		// create a new instance of bullet
		Bullet bullet = new Bullet(x,y,this.strength);

		this.bullets.add(bullet);
    }

	//method called if up/down/left/right arrow key is pressed.
	public void move() {

		if(((this.x + this.dx) >= 0) && (this.x + this.dx)<=(GameStage.WINDOW_WIDTH - this.width)){
			this.x += this.dx;
		}

		if(((this.y+this.dy) >= 35) && (this.y  +this.dy)<=(GameStage.WINDOW_HEIGHT - this.height)){
			this.y += this.dy;
		}
	}

	//reduces the strength of the ship
	public void setStrength(int value, int type){

		// if not invisible, allow damage to pass through; else, no effect.
		if (!this.powerupInvisibilityActivated){

			int temp= this.strength-=value;
			this.strength=temp;

			if(temp<=0){
				this.alive=false;
				this.visible=false;
				this.strength=0;
			}

			// Type = the int type of the powerup

			if(type==1){

				this.powerupInvisibilityActivated = true;
				this.loadImage(Ship.INVISIBLE_SHIP_TEMPORARY);       //magiging invisible yung ship
				Thread waiter = new Thread(new LimitedTimer(1, this));
				waiter.start();

			}else if(type==2){

				Media shipHit = new Media(Paths.get("src/sounds/shipHit.mp3").toUri().toString());
				MediaPlayer player6 = new MediaPlayer(shipHit);
				player6.play();
				player6.setAutoPlay(true);
				this.loadImage(SHIP_HIT);
				Thread waiter1= new Thread(new LimitedTimerHit(1,this));
				waiter1.start();
			}
		}
	}

	// Getters and setters and other functions

	 int getStrength(){
		return this.strength;
	}

	void addScore() {
		this.score++;
	}

	int getScore() {
		return this.score;
	}

	void usePearlPowerup() {
		this.strength += 50;
	}

	void useInvisibility() {
		this.powerupInvisibilityActivated = true;

		// makes the ship "invisible"
		this.loadImage(Ship.INVISIBLE_SHIP);

		this.isInvisibleFromPowerup=true;
		Thread waiter = new Thread(new LimitedTimer(3, this));
		waiter.start();
	}

	// called by the LimitedTimer; when timer is done
	void invisibilityDone() {
		this.powerupInvisibilityActivated = false;
		this.loadImage(Ship.SHIP_IMAGE);            //babalik sa dating normal image yung ship
		this.isInvisibleFromPowerup=false;
	}

	void updateDirection(int[] update) {
		int[] temp = this.directionArray;

		for (int i=0; i<4; i++) {
			temp[i] = temp[i] + update[i];
			if (temp[i] > SHIP_MAX_SPEED) {temp[i] = SHIP_MAX_SPEED;}
				else if (temp[i] < -SHIP_MAX_SPEED) {temp[i] = -SHIP_MAX_SPEED;};
		}
		this.directionArray = temp;

		//  DIRECTION-ARRAY FORMAT: [left, right, down, up]
		int [] direction = new int[2];
		direction[0] = this.directionArray[0] + this.directionArray[1];
		direction[1] = this.directionArray[2] + this.directionArray[3];

		this.setDX(direction[0]);
		this.setDY(direction[1]);

	}

	boolean isInvisibleFromPowerup() {
		return this.isInvisibleFromPowerup;
	}
}
