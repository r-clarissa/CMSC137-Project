package stages;

import stages.SuperStage;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TransitionStage {

	private Group root;
	private Scene scene;

	private GraphicsContext gc;
	private Canvas canvas;

	private Text number;

	private int tokenType;

	public TransitionStage() {
		new TransitionStage(1);
	}

	public TransitionStage(int tokenType) {
		this.root = new Group();
		this.scene = new Scene(root, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);

		this.canvas = new Canvas(SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.tokenType = tokenType;

	}

	// Method for setting up the title stage
	public void setStage(Stage stage, boolean isStart) {

		gc.clearRect(0, 0, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);

		if (isStart) gc.drawImage(SuperStage.startCountdownPage, 0, 0);
		else gc.drawImage(SuperStage.resumeCountdownPage, 0, 0);

		// 3...
		number = new Text("3...");
		designText(532, 409, number, 3);

		// 2...
		PauseTransition two = new PauseTransition(Duration.seconds(1));
		two.play();
		two.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				two();
			}
		});

		// 1...
		PauseTransition one = new PauseTransition(Duration.seconds(2));
		one.play();
		one.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				one();
			}
		});

		// start
		PauseTransition gamestart = new PauseTransition(Duration.seconds(3));
		gamestart.play();
		gamestart.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GameStage gamestage = new GameStage(tokenType);
				gamestage.setStage(stage);
			}
		});

		this.root.getChildren().addAll(canvas, number);

		stage.setTitle("The Pandemic Hero");
		stage.getIcons().add(SuperStage.icon);
		stage.setScene(this.scene);
		stage.show();

	}

	private static void designText(int x, int y, Text text, int type) {

		text.setX(x);
		text.setY(y);
		text.setFont(Font.font(SuperStage.sourceSansPro, FontWeight.BOLD, FontPosture.REGULAR, 98));

		if (type==3) {text.setFill(SuperStage.blue);}
		else if (type==2) {text.setFill(Color.rgb(255, 214, 129));}
		else {text.setFill(Color.rgb(255, 85, 79));}
	}


	private void two() {
		this.number.setText("2...");
		this.number.setFill(SuperStage.yellow);
	}

	private void one() {
		this.number.setText("1...");
		this.number.setFill(SuperStage.red);
	}


}