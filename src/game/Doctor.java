package game;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import stages.GameStage;

public class Doctor extends Sprite{
	private String name;
	private int strength;
	private int score;
	private boolean vitaminsEffect = false;

	private boolean alive;

	private ArrayList<Vaccine> vaccines;
	public final static Image DOCTOR_IMAGE = new Image("images/StaffOfHermes.png",Doctor.DOCTOR_WIDTH,Doctor.DOCTOR_WIDTH,false,false);
	private final static int DOCTOR_WIDTH = 75;

	Doctor(String name, int x, int y){
		super(x,y);
		this.name = name;
		Random r = new Random();
		this.strength = r.nextInt(151)+100;
		this.alive = true;
		this.vaccines = new ArrayList<Vaccine>();
		this.score = 0;
		this.loadImage(Doctor.DOCTOR_IMAGE);
	}

	//Called in GameTimer moveViruses
	//This is called whenever the doctor is hit by the virus.
	void deductStrength(int damage) {
		this.strength -= damage;
		System.out.println("Strength: " + this.strength);
	}

	void addStrength(int add) {
		this.strength += add;
		System.out.println("Strength: " + this.strength);
	}

	//Called in GameTimer moveVaccine
	//This is called whenever the vaccine collided with the virus.
	void addScore(int value) {
		this.score += value;
		System.out.println("Score: " + this.score);
	}

	boolean isAlive(){
		if(this.alive) return true;
		return false;
	}

	int getScore() {
		return this.score;
	}

	int getStrength(){
		return this.strength;
	}

	String getName(){
		return this.name;
	}

	void die(){
    	this.alive = false;
    }

	//method that will get the bullets 'shot' by the ship
	ArrayList<Vaccine> getVaccines(){
		return this.vaccines;
	}

	//Called in moveMyShip
	//method called if spacebar is pressed
	void shoot(){
		//compute for the x and y initial position of the bullet
		int x = (int) (this.x + this.width/2);
		int y = (int) (this.y + this.height/2);
		/*
		 * TODO: Instantiate a new bullet and add it to the bullets arraylist of ship
		  */
		Vaccine vc = new Vaccine(x,y); // Instantiate a new bullet
		this.vaccines.add(vc); // and add it to the bullets arraylist of vaccine
    }

	void setVitaminsEffect(boolean effect) {
		this.vitaminsEffect = effect;
	}

	boolean getVitaminsEffect() {
		return this.vitaminsEffect;
	}

	//called in handle in GameTimer
	//method called if up/down/left/right arrow key is pressed.
	void move() {
		/*
		 *TODO:
		 *
		 */
		if(this.getX() < (GameStage.WINDOW_WIDTH-75) && this.getX() > 0){ // Only change the x and y position of the ship if the current x,y position
			if(this.getY() < (GameStage.WINDOW_HEIGHT-75) && this.getY() > 45){ // is within the gamestage width and height so that the ship won't exit the screen
				this.x += this.dx;
				this.y += this.dy;
			}else if(this.getY() < 75){
				this.y += 5;
			}else if(this.getY() >= (GameStage.WINDOW_HEIGHT-100)){
				this.y -= 5;
			}
		}else if(this.getX() < 1){
			this.x += 5;
		}else if(this.getX() >= (GameStage.WINDOW_WIDTH-100)){
			this.x -= 5;
		}
	}
}
