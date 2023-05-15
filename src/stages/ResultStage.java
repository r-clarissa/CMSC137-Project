package stages;

import stages.SuperStage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Note: When initializing results stage, set the score first before the stage.

public class ResultStage {

	private Group root;
	private Scene scene;

	private GraphicsContext gc;
	private Canvas canvas;

	private Integer score;

	public ResultStage() {
		this.root = new Group();
		this.scene = new Scene(root, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);

		this.canvas = new Canvas(SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	}

	// Method for setting up the title stage
	public void setStage(Stage stage, boolean isWinner) {

		gc.clearRect(0, 0, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);

		Text score = new Text(this.score.toString());
		if (isWinner) {
			gc.drawImage(SuperStage.winnerPage, 0, 0);
			designText(score, SuperStage.yellow);
		}
		else {
			gc.drawImage(SuperStage.gameoverPage, 0, 0);
			designText(score, SuperStage.red);
		}

		// Play Again Btn
		Button playAgainBtn = new Button("Play Again");
		SuperStage.designBtn(422, 375, playAgainBtn);

		playAgainBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				SelectTokenStage selectTokenStage = new SelectTokenStage();
				selectTokenStage.setStage(stage);
			}
		});

		// Back to Main Menu
		Button backMainMenu = new Button("Back to Main Menu");
		SuperStage.designBtn(422, 466, backMainMenu);

		backMainMenu.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				TitleScreen titleScreen = new TitleScreen();
				titleScreen.setStage(stage);
			}
		});

		// Exit Btn
		Button exitBtn = new Button("Exit Game");
		SuperStage.designBtn(422, 557, exitBtn);

		exitBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				System.exit(0);
			}
		});

		this.root.getChildren().addAll(canvas, score, playAgainBtn, backMainMenu, exitBtn);

		stage.setTitle("The Pandemic Hero");
		stage.getIcons().add(SuperStage.icon);
		stage.setScene(this.scene);
		stage.show();

	}

	public void setScore(Integer score) {
		this.score = score;
	}

	private void designText(Text text, Color color) {

		Font font = Font.font("Source Sans Pro", FontWeight.BOLD, FontPosture.REGULAR, 65);

		text.setX(681);
		text.setY(336);
		text.setFont(font);
		text.setFill(color);
		text.setStroke(Color.BLACK);
		text.setStrokeWidth(0.25);
	}

}
