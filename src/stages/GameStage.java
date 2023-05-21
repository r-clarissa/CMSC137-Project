package stages;

import game.GameTimer;
import stages.SuperStage;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStage {

	public final static int WINNER = 1;
	public final static int LOSE = 2;

	private Stage stage;

	public final static Image icon = new Image("images/Virus.png");
	public final static Image blank = new Image("images/Blank.png", 0, 0, false, false);

	private Group root;
	private Scene scene;
	private GameTimer gametimer;

	private GraphicsContext gc;
	private Canvas canvas;

	private ImageView prompt;

	private Text life;
	private Text strength;
	private Text score;

	private int tokenType;

	public GameStage(int tokenType) {
		this.prompt = new ImageView();
		this.life = new Text();
		this.strength = new Text();
		this.score = new Text();

		this.root = new Group();
		this.scene = new Scene(root, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);

		this.canvas = new Canvas(SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();

		this.tokenType = tokenType;

		this.gametimer = new GameTimer(this.root, this.gc, this.scene, this, tokenType);
	}

	// Method for setting up the title stage
	public void setStage(Stage stage) {

		gc.clearRect(0, 0, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		gc.drawImage(SuperStage.gameStagePage, 0, 0);

//		// Life
		designText(this.life, 134, 70, SuperStage.red);
		setLife(0);

//		// Strength
		designText(this.strength, 611, 70, SuperStage.yellow);
		setStrength(0);
//
//		// Score
		designText(this.score, 1072, 70, SuperStage.blue);
		setScore(0);

		// Display Prompt

//		this.root.getChildren().addAll(canvas, life, strength, score, prompt);
//
//		stage.setTitle("The Pandemic Hero");
//		stage.getIcons().add(SuperStage.icon);
//		stage.setScene(this.scene);
//		stage.show();

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

	// Designs texts displayed
	private void designText(Text text, int x, int y, Color color) {

		Font font = Font.font("Source Sans Pro", FontWeight.BOLD, FontPosture.REGULAR, 42);

		text.setX(x);
		text.setY(y);
		text.setFont(font);
		text.setFill(color);
		text.setStroke(Color.BLACK);
		text.setStrokeWidth(1.0);

		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(4);
		dropshadow.setOffsetY(4);
		dropshadow.setRadius(4);
		dropshadow.setColor(Color.rgb(0, 0, 0, 0.25));
		text.setEffect(dropshadow);

	}


	// Sets the life being displayed. Call this to update life being displayed
	public void setLife(Integer life) {
		this.life.setText(life.toString());
	}

	// Sets the strength being displayed. Call this to update strength being displayed
	public void setStrength(Integer strength) {
		this.strength.setText(strength.toString());
	}

	// Sets the score being displayed. Call this to update score being displayed
	public void setScore(Integer score) {
		this.score.setText(score.toString());
	}

	// Prompt the arrival of Boss 1 for 2 seconds
	private void displayPrompt(int promptNumber, int startSecond) {

		PauseTransition prompt = new PauseTransition(Duration.seconds(startSecond));

		prompt.play();
		prompt.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setPrompt(promptNumber);
			}
		});

		// Remove the prompt after 2 seconds
		PauseTransition noPrompt = new PauseTransition(Duration.seconds(startSecond+2));

		noPrompt.play();
		noPrompt.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				setPrompt(SuperStage.NULL);
			}
		});

	}

	// Set Prompt
	// 0 - No Prompt; 1 - Boss 1 Prompt; 2 - Boss 2 Prompt; 3 - Boss 3 Prompt; 4 - Demand Prompt
	private void setPrompt(int promptNumber) {
		if (promptNumber == SuperStage.NULL) this.prompt.setImage(null);
		else if (promptNumber == SuperStage.BOSS1) this.prompt.setImage(SuperStage.boss1Prompt);
		else if (promptNumber == SuperStage.BOSS2) this.prompt.setImage(SuperStage.boss2Prompt);
		else if (promptNumber == SuperStage.BOSS3) this.prompt.setImage(SuperStage.boss3Prompt);
		else if (promptNumber == SuperStage.DEMAND) this.prompt.setImage(SuperStage.demandPrompt);
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