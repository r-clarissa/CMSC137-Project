package application;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage {
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;

	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;

	//class constructor
	GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.WHITESMOKE);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.gametimer = new GameTimer(this.gc,this.scene,this);
	}

	//method to add the stage elements
	void setStage(Stage stage) {
		this.stage = stage;

		//set stage elements here
		this.root.getChildren().add(canvas);

		this.stage.setTitle("Warfare with the Undead");
		this.stage.setScene(this.scene);

		//invoke the start method of the animation timer
		this.gametimer.start();

		this.stage.show();
	}

	//method that shows the GameOverStage
	void flashGameOver(int ending, int score){
		FadeTransition ft = new FadeTransition(Duration.seconds(3), this.canvas); //create a fade transition
		ft.setFromValue(1.0); //starting opacity of the fade transition
		ft.setToValue(0); //ending opacity of the fade transition
		ft.setCycleCount(1); //number of cycles of the animation
		ft.setAutoReverse(false); //determines if the animation reverses
		ft.play();

		ft.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GameOverStage gameOver = new GameOverStage(ending, score);
				gameOver.setStage(stage);
			}

		});
	}
}

