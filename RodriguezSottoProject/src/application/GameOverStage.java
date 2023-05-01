package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class GameOverStage {
	private int flag;
	private Scene scene;
	private VBox root;
	private Stage stage;
	private Canvas canvas;
	private int score;

	public final static String EXIT = "Exit";

	GameOverStage(int ending, int score) {
		this.root = new VBox();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, Color.WHITE);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.root.getChildren().add(this.canvas);
		this.flag = ending;
		this.score = score;
	}

	//method that will set the stage
	void setStage(Stage stage) {
		this.stage = stage;

		if(this.flag == 1) {
			this.stage.setTitle("Victory!");
			System.out.println("Victory!");
		} else {
			this.stage.setTitle("Defeat!");
			System.out.println("Defeat!");
		}
		this.setProperties();

		this.stage.setScene(this.scene);
		this.stage.setResizable(false);
		this.stage.show();
	}

	//method that will create the scene
	private void setProperties() {
		StackPane root = new StackPane();
		root.getChildren().addAll(this.createCanvas(), this.createVBox());
		this.scene = new Scene(root);
	}

	//method that will create the two different canvas depending whether the player wins or the player loses
	private Canvas createCanvas() {
		Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		if(this.flag == 1) {
			Image bg = new Image("images/winner.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
			gc.drawImage(bg, 0, 0);
		} else {
			Image bg = new Image("images/loser.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
			gc.drawImage(bg, 0, 0);
		}

		gc.setFont(Font.font("Impact", FontWeight.NORMAL, 30));
		gc.setFill(Color.LAVENDER);
		gc.fillText("Score: ", 20, 420);
		gc.setFont(Font.font("Impact", FontWeight.NORMAL, 30));
		gc.setFill(Color.LAVENDER);
		gc.fillText(this.score + "", 105, 420);

		return canvas;
	}

	//method that will return the VBox that contains the exit button
	private VBox createVBox() {
		SplashScreen s = new SplashScreen();

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.BOTTOM_LEFT);
		vbox.setPadding(new Insets(20));
		vbox.setSpacing(8);

		Button b1 = new Button("Exit");
		b1.setPrefSize(SplashScreen.BUTTON_WIDTH, SplashScreen.BUTTON_HEIGHT); //set the size of the button
		b1.setFont(Font.font("Impact", FontWeight.LIGHT, 15)); //change the font of the text in the button
		b1.setBackground(s.createBackground()); //set the background of the button

		vbox.getChildren().add(b1);

		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println(GameOverStage.EXIT);
				System.exit(0);
			}
		});

		return vbox;
	}

}
