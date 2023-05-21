package game;

import javafx.scene.image.Image;
import stages.GameStage;
import stages.SuperStage;

public class Vaccine extends Sprite {
	private final int VACCINE_SPEED = 4;
	private final static Image VACCINE_IMAGE = new Image("images/vaccine.png",Vaccine.VACCINE_WIDTH,Vaccine.VACCINE_HEIGHT,false,false);
	private final static int VACCINE_WIDTH = 50;
	private final static int VACCINE_HEIGHT = 20;


	Vaccine(int x, int y){
		super(x,y);
		this.loadImage(Vaccine.VACCINE_IMAGE);
	}

	//called in handle in GameTimer
	//method that will move/change the x position of the bullet
	void move(){
		/*
		 * TODO:
		 */
		this.x += VACCINE_SPEED; // Change the x position of the bullet depending on the bullet speed.
		if(this.getX() > SuperStage.WINDOW_WIDTH){ // If the x position has reached the right boundary of the screen,
			this.setVisible(false); // set the bullet's visibility to false.
		}

	}


}
