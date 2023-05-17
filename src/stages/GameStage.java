package stages;

import game.GameTimer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage {
	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;
	private Group root;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Stage stage;
	private GameTimer gametimer;

	public final static int WINNER = 1;
	public final static int LOSE = 2;

	public final static Image bg = new Image("images/Background.png", 800, 500, false, false);
	public final static Image bg2 = new Image("images/Hospital.png", 800, 500, false, false);
	public final static Image icon = new Image("images/Virus.png");
	public final static Image blank = new Image("images/Blank.png", 0, 0, false, false);

	//the class constructor
	GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		//instantiate an animation timer
		this.gametimer = new GameTimer(this.root, this.gc, this.scene, this);
	}

	//method to add the stage elements
	void setStage(Stage stage) {
		this.stage = stage;

		this.root.getChildren().add(canvas);

		this.stage.setTitle("The Pandemic Game");		// This sets the title of this application
		this.stage.setScene(this.scene);
		this.stage.getIcons().add(icon);				// This sets the icon for this application
		this.stage.setResizable(false);					// Disables the stage to be resized

		//invoke the start method of the animation timer
		this.gametimer.start();

		this.stage.show();

	}


	public void flashResults(int num){ //
		PauseTransition transition = new PauseTransition(Duration.seconds(1));
		transition.play();
		transition.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				ResultStage gameover = new ResultStage(num);
				stage.setScene(gameover.getScene());
			}
		});
	}



}

