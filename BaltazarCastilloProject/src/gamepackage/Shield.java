package gamepackage;

import javafx.scene.image.Image;

public class Shield extends PowerUp{
	public final static Image INVISIBILITY_IMAGE = new Image("images/invisibility.png",PowerUp.POWERUP_WIDTH,PowerUp.POWERUP_WIDTH,false,false);


	public Shield(int x, int y, Ship myship, int type){
		super(x,y,myship,type);
		this.loadImage(INVISIBILITY_IMAGE);

	}


}
