package stages;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class About {
	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	About(){
		this.root = new Group();
		this.scene = new Scene(root,SuperStage.WINDOW_WIDTH,SuperStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(SuperStage.WINDOW_WIDTH,SuperStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	}


	public void setStage(Stage stage){
		gc.clearRect(0, 0, 1200, 700);
		gc.drawImage(SuperStage.aboutPage, 0, 0);

		//Back To Main Button
		Button backbtn = new Button("Back");
		SuperStage.designBtn(790, 540, backbtn);

		// Do this when the button is clicked
		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				TitleScreen ts = new TitleScreen();
				ts.setStage(stage);
			}
		});

		this.root.getChildren().addAll(canvas,backbtn);
		stage.setTitle("The Pandemic Hero - About");
		stage.getIcons().add(GameStage.icon);
		stage.setScene(this.scene);
		stage.show();
	}
}
