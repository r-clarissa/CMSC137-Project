package gamepackage;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {

	protected Image img;
	protected Image boom;
	protected Image poof;
	protected int x, y, dx, dy;
	protected boolean visible;
	protected boolean boomVisible;
	protected double width;
	protected double height;
	protected boolean collided;
	protected boolean hasHP=false;

	public Sprite(int xPos, int yPos){
		this.x = xPos;
		this.y = yPos;
		this.visible = true;
		this.boomVisible=false;
		this.collided=false;
	}

	//method to set the object's image
	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}

	//method to set the image to the image view node
	protected void render(GraphicsContext gc){
		if (this.visible) {
		gc.drawImage(this.img, this.x, this.y);		// only render if element is visible
		}
    }

	protected void loadBoomImage(Image img){
		try{
			this.x = this.x + 8;
			this.y = this.y - 8;
			this.boom = img;
	        this.setSizeBoom();
		} catch(Exception e){}
	}
	void renderBoom(GraphicsContext gc,int xBoom, int yBoom){
		if(this.boomVisible){
			gc.drawImage(this.boom, xBoom, yBoom);
		}
	}

	//method to set the object's width and height properties
	private void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}

	private void setSizeBoom(){
		this.width = this.boom.getWidth();
	    this.height = this.boom.getHeight();
	}

	//method that will check for collision of two sprites
	boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();


		boolean intersects = rectangle1.intersects(rectangle2);

		if (intersects && (this.collided==false) && (rect2.collided==false) && (this.hasHP == false) && (rect2.hasHP == false)) {
			// case of bullet x smaller enemy
			this.setCollided();
			rect2.setCollided();
		} else if (intersects && (this.collided==false) && (rect2.collided==false) && (this.hasHP == true) && (rect2.hasHP == false)) {
			// case of bullet x boss enemy: Do nothing and make sure intersect remains true
		} else if (intersects && (this.collided==false) && (rect2.collided==false) && (this.hasHP == false) && (rect2.hasHP == true)) {
			// case of powerup collides ship, Do nothing and make sure intersect remains true
		} else if (intersects && (this.collided==false) && (rect2.collided==false) && (this.hasHP == true) && (rect2.hasHP == true)) {
			// case of boss collides ship, Do nothing and make sure intersect remains true
		} else {
			intersects = false;
		}

		// Result: Bullet only collides with one enemy!!
		return(intersects);

	}

	private void setCollided() {
		this.collided = true;
	}

	//method that will return the bounds of an image
	private Rectangle2D getBounds(){
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}

	//getters
	int getX() {
    	return this.x;
	}

	int getY() {
    	return this.y;
	}

	boolean getVisible(){
		return visible;
	}

	boolean isVisible(){
		if(visible) return true;
		return false;
	}

	//setters
	void setDX(int dx){
		this.dx = dx;
	}

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



}
