package gamepackage;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeScene extends AnimationTimer{

	private int currentBgPositionX;	// for the counter of background image position
	private ImageView backgroundView;
	public final static Image BACKGROUND = new Image("images/Background/Layered_Background_Pic.jpg",7585,GameStage.WINDOW_HEIGHT,false,false);
	Scene welcomeScene;
	GraphicsContext gc;

	WelcomeScene(GraphicsContext gc, Scene theScene, ImageView backgroundView){
		this.welcomeScene=theScene;
		this.gc=gc;
		this.currentBgPositionX = 0;
		this.backgroundView = backgroundView;
	}

	@Override
	public void handle(long currentNanoTime) {
		this.backgroundPositionUpdate();
	}

	private void backgroundPositionUpdate() {
		currentBgPositionX--;
		this.backgroundView.setLayoutX(currentBgPositionX);
	}
}
