package stages;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class About {
	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;
	private Image content = new Image ("images/AboutContents.png", GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT, false, false);

	About(){
		this.root = new Group();
		this.scene = new Scene(root,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	}


	public void setStage(Stage stage){
		gc.clearRect(0, 0, 800, 500);
		gc.drawImage(content, 0, 0);

		//Back To Main Button
		Button backbtn = new Button("BACK TO MAIN");
		Font btnFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 30);

		// Set the style for the button
		backbtn.setStyle("-fx-background-color: transparent;");			// Set background button to transparent
		backbtn.setFont(btnFont);		//Set font style
		backbtn.setLayoutX(580);	//Set X
		backbtn.setLayoutY(435);	//Set Y

		backbtn.setTextFill(Color.rgb(248, 188, 36));

		// Do this when the button is clicked
		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				TitleScreen ts = new TitleScreen();
				ts.setStage(stage);
			}
		});

		this.root.getChildren().addAll(canvas,backbtn);
		stage.setTitle("The Pandemic Game - About");
		stage.getIcons().add(GameStage.icon);
		stage.setScene(this.scene);
		stage.show();
	}
}
