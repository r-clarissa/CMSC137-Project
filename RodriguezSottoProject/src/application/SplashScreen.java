package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SplashScreen {
	public static final String NEW_GAME = "New Game";
	public static final String INSTRUCTION = "How to Play";
	public static final String ABOUT = "About";
	public static final String BACK = "Menu";

	public static final int BUTTON_HEIGHT = 40;
	public static final int BUTTON_WIDTH = 150;

	private Scene splashScene;
	private Stage stage;
	private VBox root;
	private Canvas canvas;

	public SplashScreen() {
		this.root = new VBox();
		this.splashScene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, Color.WHITE);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.root.getChildren().add(this.canvas);
	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
		stage.setTitle("Warfare with the Undead");
		this.initSplash(stage);

		stage.setScene(this.splashScene);
		stage.setResizable(false);
		stage.show();
	}

	//method that initializes the splash screen
	private void initSplash(Stage stage) {
		StackPane root = new StackPane();
		root.getChildren().addAll(this.createCanvas(), this.createVBox());
		this.splashScene = new Scene(root);
	}

	//method that will create the canvas
	private Canvas createCanvas() {
		Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image bg = new Image("images/splash.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
		gc.drawImage(bg, 0, 0);
		return canvas;
	}

	//method that will create the background for the buttons
	Background createBackground() {
		Image bt = new Image("images/buttonf.png");

		BackgroundSize bs = new BackgroundSize(BUTTON_WIDTH, BUTTON_HEIGHT, false, false, true, true);
		BackgroundImage bi = new BackgroundImage(bt, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bs);
		Background b = new Background(bi);

		return b;
	}

	//method that creates the buttons in the splash screen and the eventHandler for each button
	private VBox createVBox() {

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER_LEFT);
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);

		Button b1 = new Button(SplashScreen.NEW_GAME);
		Button b2 = new Button(SplashScreen.INSTRUCTION);
		Button b3 = new Button(SplashScreen.ABOUT);

		//set the sizes of button
		b1.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		b2.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		b3.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

		b1.setFont(Font.font("Impact", FontWeight.LIGHT, 20)); //set the font of the button
		b1.setBackground(this.createBackground()); //set the background of the button
		b2.setFont(Font.font("Impact", FontWeight.LIGHT, 20));
		b2.setBackground(this.createBackground());
		b3.setFont(Font.font("Impact", FontWeight.LIGHT, 20));
		b3.setBackground(this.createBackground());

		vbox.getChildren().addAll(b1, b2, b3);

		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setGame(stage, 1);
			}
		});
		b2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setGame(stage, 2);
			}
		});
		b3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				setGame(stage, 3);
			}
		});

		return vbox;
	}

	//method that will set the different scenes and stages
	private void setGame(Stage stage, int screen) {
		stage.setScene(this.splashScene);

		switch(screen) {
			case 1:
				System.out.println(SplashScreen.NEW_GAME);
				GameStage gameStage = new GameStage();
				gameStage.setStage(stage);
				break;
			case 2:
				System.out.println(SplashScreen.INSTRUCTION);
				MenuOptions option1 = new MenuOptions();
				option1.setStage(stage, 0);
				break;
			case 3:
				System.out.println(SplashScreen.ABOUT);
				MenuOptions option2 = new MenuOptions();
				option2.setStage(stage, 1);
				break;
		}
	}
}
