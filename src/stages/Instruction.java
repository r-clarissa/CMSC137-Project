package stages;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Instruction {
	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	Instruction(){
		this.root = new Group();
		this.scene = new Scene(root, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(1200,700);
		this.gc = canvas.getGraphicsContext2D();
	}

	void setStage(Stage stage){
		gc.clearRect(0, 0, 1200, 700);
		gc.drawImage(SuperStage.instructionPage1, 0, 0);

		//BACK BUTTON
		Button backbtn = new Button("Back");
		SuperStage.designBtn(100, 540, backbtn);

		Button nextbtn = new Button("Next");
		SuperStage.designBtn(790, 540, nextbtn);

		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				TitleScreen ts = new TitleScreen();
				ts.setStage(stage);
			}
		});

		nextbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				InstructionPage2 next = new InstructionPage2();
				next.setStage(stage);
			}
		});

		this.root.getChildren().addAll(canvas, backbtn, nextbtn);
		stage.setTitle("The Pandemic Hero - Instructions");
		stage.getIcons().add(GameStage.icon);
		stage.setScene(this.scene);
		stage.show();
	}

}
