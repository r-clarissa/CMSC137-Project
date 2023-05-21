package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import stages.GameStage;

public class Sprite {
	protected Image img;
	protected int x, y, dx, dy;
	protected boolean visible;
	protected double width;
	protected double height;

	//called in vaccine constructor and virus contructor
	Sprite(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
		this.visible = true;
	}

	//called in virus constructor and vaccine constructor
	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}

	//called in handle, renderViruses, and renderVaccines in GameTimer.
	//method to set the image to the image view node
	void render(GraphicsContext gc){
		//gc.drawImage(GameStage.bg, 0, 0);
		gc.drawImage(this.img, this.x, this.y);

    }

	void hideImage(Sprite sprite, int x, int y) {
		sprite.setVisible(false);
		sprite.loadImage(GameStage.blank);	//Hide image virus image if there is collision
		sprite.setXY(x, y);
	}

	//called in loadImage() in this class
	//method to set the object's width and height properties
	private void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}


	//method that will check for collision of two sprites
	boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();

		return rectangle1.intersects(rectangle2);
	}

	//Called in collidesWith
	//method that will return the bounds of an image
	private Rectangle2D getBounds(){
		return new Rectangle2D(this.x-7, this.y-7, this.width-7, this.height-7);
	}

	//getters
	protected int getX() {
    	return this.x;
	}

	protected int getY() {
    	return this.y;
	}

	boolean getVisible(){
		return visible;
	}

	boolean isVisible(){
		if(visible) return true;
		return false;
	}

	//Called in moveMyShip() in GameTimer
	//setters
	void setDX(int dx){
		this.dx = dx;
	}

	//Called in moveMyShip() in GameTimer
	void setDY(int dy){
		this.dy = dy;
	}

	void setWidth(double val){
		this.width = val;
	}

	void setHeight(double val){
		this.height = val;
	}

	void setVisible(boolean value){
		this.visible = value;
	}

	void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

}
