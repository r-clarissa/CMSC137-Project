package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;

public class Shooter extends Sprite{
	private String name;
	private int strength;
	private boolean alive;
	private ArrayList<Bullet> bullets;
	private int score = 0;

	// added for love bomb power up
	private boolean invincibility;
	private int invincibilityElapsed;
	private final static int INVINCIBILITY_DURATION = 3;

	public final static Image SHOOTER_IMAGE = new Image("images/shooter-ff.gif",Shooter.SHOOTER_SIZE,Shooter.SHOOTER_SIZE,false,false);
	private final static int SHOOTER_SIZE = 70;


	Shooter(String name, int x, int y){
		super(x,y,Shooter.SHOOTER_IMAGE);
		this.name = name;
		Random r = new Random();
		this.strength = r.nextInt(51)+100;
		this.alive = true;
		this.invincibility = false; // added for love bomb power up
		this.bullets = new ArrayList<Bullet>();
		System.out.println("Strength: " + this.strength); // added to check the strength
	}

	//method that will set the boolean attribute alive to false
	void die(){
    	this.alive = false;
    }

	//method that will return the boolean value of the boolean attribute alive
	boolean isAlive(){
		if(this.alive) return true;
		return false;
	}

	//method that will check the boolean value of the boolean attribute invincibility
	boolean isInvincible() {
		return this.invincibility;
	}

	//getters
	String getName(){
		return this.name;
	}

	ArrayList<Bullet> getBullets(){
		return this.bullets;
	}

	int getStrength() {
		return this.strength;
	}

	int getScore() {
		return this.score;
	}

	//setter
	void setScore() {
		this.score+=1;
	}

	//method for shooting
	void shoot(){
		int x = (int) (this.x + this.width+10);
		int y = (int) (this.y + this.height/3);

		Bullet b = new Bullet(x, y); //create a new bullet
		this.bullets.add(b); //add the new instance to the ArrayList of bullets
    }

	//method that will change the position of the shooter
	void move() {
		if((this.x+this.dx >= 0 && this.y+this.dy >= 0) && (this.x+this.dx <= GameStage.WINDOW_WIDTH-this.width && this.y+this.dy <= GameStage.WINDOW_HEIGHT-this.height)) {
			this.x += this.dx;
			this.y += this.dy;
		}
	}

	//method for increasing the shooter's strength
	void increaseStrength() { //collected an energy drink
		this.strength+=50;
		System.out.println("Strength: " + this.strength);
	}

	//method for decreasing the shooter's strength
	void decreaseStrength(int type) {
		if(type == 0) { // mini zombie hits the shooter
			this.strength-=30;
		} else { // boss zombie hits the shooter
			this.strength-=50;
			this.makeInvincible();
		}
		// to check shooter's strength
		if(this.getStrength() <= 0) {
			System.out.println("Strength: 0");
			this.die();
		} else
			System.out.println("Strength: " + this.strength);

	}

	/*method for incrementing the boolean attribute invincibility by 1 and checking if the integer attribute
	  invincibilityElapsed is equal to the duration of the power up*/
	void setInvincibilityElapsed() {
		this.invincibilityElapsed += 1;
		if(this.invincibilityElapsed == Shooter.INVINCIBILITY_DURATION) {
			this.invincibility = false;
		}
	}

	/*method for setting the boolean attribute invincibility to true and the integer attribute
	  invincibilityElapsed to 0*/
	void makeInvincible() {
		this.invincibility = true;
		this.invincibilityElapsed = 0;
	}

}