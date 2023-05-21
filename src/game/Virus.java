package game;

import javafx.scene.image.Image;
import stages.GameStage;
import stages.SuperStage;

import java.util.Random;

public class Virus extends Sprite {
	public static final int MAX_VIRUS_SPEED = 5;
	public final static Image VIRUS_REGULAR = new Image("images/Virus 1.png",Virus.VIRUS_WIDTH,Virus.VIRUS_WIDTH,false,false);
	public final static Image VIRUS_BULLET = new Image("images/Virus 1.png",60,60,false,false);
	public final static int VIRUS_WIDTH=35;

	public static final int STRENGTH_DEDUCTION = 30;
	public static final int BULLET_TYPE = 1;
	public static final int REGULAR_TYPE = 0;

	private int type = 0;
	private boolean alive;
	//attribute that will determine if a fish will initially move to the right
	private boolean moveRight;
	private int speed;

	//Called in spawnViruses from GameTimer constructor
	Virus(int x, int y, int type){
		super(x,y);
		this.alive = true;
		this.type = type;
		if (this.type==REGULAR_TYPE) loadImage(Virus.VIRUS_REGULAR);
		else loadImage(Virus.VIRUS_BULLET);
		/*
		 *TODO: Randomize speed of fish and moveRight's initial value
		 */
		Random rd = new Random();
		this.speed = rd.nextInt(Virus.MAX_VIRUS_SPEED) + 1;
	}

	//called in handle in GameTimer
	//method that changes the x position of the fish
	void move(){
		/*
		 * TODO: 				If moveRight is true and if the virus hasn't reached the right boundary yet,
		 *    						move the virus to the right by changing the x position of the virus depending on its speed
		 *    					else if it has reached the boundary, change the moveRight value / move to the left
		 * 					 Else, if moveRight is false and if the virus hasn't reached the left boundary yet,
		 * 	 						move the virus to the left by changing the x position of the virus depending on its speed.
		 * 						else if it has reached the boundary, change the moveRight value / move to the right
		 */
//		if (this.x > 10)
//		this.x -= speed;
 		if (this.getVisible()) {
 			if(this.moveRight == true){ // If moveRight is true and
				if(this.getX() < SuperStage.WINDOW_WIDTH){ // if the fish hasn't reached the right boundary yet
					this.x += this.speed; // move the fish to the right by changing the x position of the fish depending on its speed
				}else if(this.getX() >= SuperStage.WINDOW_WIDTH){ // else if it has reached the boundary, change the moveRight value / move to the left
					this.moveRight = false;
				}
			} else if(this.moveRight == false){ // Else, if moveRight is false and
				if(this.getX() > 0){ // if the fish hasn't reached the left boundary yet,
					this.x -= this.speed; //move the fish to the left by changing the x position of the fish depending on its speed.
				}else if(this.getX() <= 0){ // else if it has reached the boundary, change the moveRight value / move to the right
					this.moveRight = true;
				}
			}
 		}

	}

	//called in moveViruses in GameTimer
	//getter
	boolean isAlive(){
		return this.alive;
	}

	//setter for speed overriding the initial speed
	void setSpeed(int speed) {
		this.speed = speed;
	}

}