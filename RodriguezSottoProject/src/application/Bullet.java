package application;

import javafx.scene.image.Image;

public class Bullet extends Sprite {
	private final int BULLET_SPEED = 20;

	public final static Image BULLET_IMAGE = new Image("images/bullet2.gif",Bullet.BULLET_WIDTH,Bullet.BULLET_HEIGHT,false,false); // not sure if the bullet is visible due to bg's color
	public final static int BULLET_WIDTH = 40;
	public final static int BULLET_HEIGHT = 10;

	Bullet(int x, int y){
		super(x,y,Bullet.BULLET_IMAGE);
	}

	/*method that will change the position of the bullet and will set the visibility to false when it
	reaches the right boundary*/
	void move(){
		this.x += this.BULLET_SPEED;
		if(this.x >= GameStage.WINDOW_WIDTH-this.width){
			this.setVisible(false);
		}
	}
}