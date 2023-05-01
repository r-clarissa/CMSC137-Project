package gamepackage;

import javafx.scene.image.Image;

public class Bullet extends Sprite {
	public final static Image BULLET_IMAGE = new Image("images/Non-animated images/Bullet_1.png",Bullet.BULLET_WIDTH,Bullet.BULLET_WIDTH,true,false);
	public final static Image BULLET_IMPACT1 = new Image("images/Non-animated images/Shoot_Animation_1.png",Bullet.BULLET_WIDTH+20,Bullet.BULLET_WIDTH+20,true,false);
	public final static Image BULLET_IMPACT2 = new Image("images/Non-animated images/Shoot_Animation_2.png",Bullet.BULLET_WIDTH+20,Bullet.BULLET_WIDTH+20,true,false);
	public final static Image BULLET_IMPACT3 = new Image("images/Non-animated images/Shoot_Animation_3.png",Bullet.BULLET_WIDTH+20,Bullet.BULLET_WIDTH+20,true,false);
	public final static Image BULLET_IMPACT4 = new Image("images/Non-animated images/Shoot_Animation_4.png",Bullet.BULLET_WIDTH+20,Bullet.BULLET_WIDTH+20,true,false);

	private final int BULLET_SPEED = 20;
	public final static int BULLET_WIDTH = 20;

	public Bullet(int x, int y,int damage){
		super(x,y);
		this.loadImage(Bullet.BULLET_IMAGE);
		this.move();
	}

	//method that will move/change the x position of the bullet
	void move(){

		// Update the x position of the bullet in the GC
		this.x = this.x + BULLET_SPEED;

		// If the x position has reached the right boundary of the screen, set the bullet's visibility to false.
		if (this.x >= GameStage.WINDOW_WIDTH) {
			this.visible = false;
		}

	}
}