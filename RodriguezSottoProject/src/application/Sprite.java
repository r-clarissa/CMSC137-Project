package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	protected Image img;
	protected int x, y, dx, dy;
	protected boolean visible;
	protected double width;
	protected double height;

	Sprite(int xPos, int yPos, Image image){
		this.x = xPos;
		this.y = yPos;
		this.loadImage(image);
		this.visible = true;
	}

	protected void loadImage(Image img){
		try{
			this.img = img;
	        this.setSize();
		} catch(Exception e){}
	}

	void render(GraphicsContext gc){
		gc.drawImage(this.img, this.x, this.y);

    }

	private void setSize(){
		this.width = this.img.getWidth();
	    this.height = this.img.getHeight();
	}

	boolean collidesWith(Sprite rect2)	{
		Rectangle2D rectangle1 = this.getBounds();
		Rectangle2D rectangle2 = rect2.getBounds();

		return rectangle1.intersects(rectangle2);
	}

	private Rectangle2D getBounds(){
		return new Rectangle2D(this.x, this.y, this.width, this.height);
	}

	//getters
	Image getImage(){
		return this.img;
	}

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
