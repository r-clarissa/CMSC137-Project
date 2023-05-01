package gamepackage;

import javafx.scene.image.Image;

public class Hp extends PowerUp{
	public final static Image PEARL_IMAGE = new Image("images/Heart.png",PowerUp.POWERUP_WIDTH,PowerUp.POWERUP_WIDTH,false,false);


	public Hp(int x, int y, Ship myship,int type){
		super(x,y,myship,type);

		this.loadImage(PEARL_IMAGE);


	}
}
