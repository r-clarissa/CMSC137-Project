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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MenuOptions {
	private Scene scene;
	private VBox root;
	private Stage stage;
	private Canvas canvas;
	private int option;

	MenuOptions() {
		this.root = new VBox();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT, Color.WHITE);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.root.getChildren().add(this.canvas);
	}

	//method to add the stage elements
	void setStage(Stage stage, int option) {
		this.option = option;
		this.stage = stage;

		if(option == 1)
			this.stage.setTitle("About");
		else
			this.stage.setTitle("How To Play");

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

	//method that will create the canvas
	private Canvas createCanvas() {
		Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		if(this.option == 1) {
			Image bg = new Image("images/about.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
			gc.drawImage(bg, 0, 0);
		} else {
			Image bg = new Image("images/howtoplay.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
			gc.drawImage(bg, 0, 0);
		}
		return canvas;
	}

	//method that will create the menu button that will return to the splash screen when clicked
	private VBox createVBox() {
		SplashScreen s = new SplashScreen();

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.BOTTOM_CENTER);
		vbox.setPadding(new Insets(20));
		vbox.setSpacing(8);

		Button b1 = new Button("Menu");

		b1.setPrefSize(SplashScreen.BUTTON_WIDTH, SplashScreen.BUTTON_HEIGHT); //set the size
		b1.setFont(Font.font("Impact", FontWeight.LIGHT, 15)); //set the font of the text
		b1.setBackground(s.createBackground()); //set the background

		vbox.getChildren().add(b1);

		b1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println(SplashScreen.BACK);
				//SplashScreen splashScreen = new SplashScreen();
				s.setStage(stage);;
			}
		});

		return vbox;
	}

}
