package stages;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ResultStage {
	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	ResultStage (int num){
		this.root = new Group();
		this.scene = new Scene(root, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.setStage(num);

	}

	private void setStage(int outcome){
		// TODO Auto-generated method stub
		gc.clearRect(0, 0, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);

		if(outcome == GameStage.WINNER){
			gc.drawImage(SuperStage.winner, 0, 0);
		} else if (outcome == GameStage.LOSE){
			gc.drawImage(SuperStage.loser, 0, 0);
		}

		Button exitbtn = new Button("Exit");
		SuperStage.designBtn(440, 540, exitbtn);

//		Button backbtn = new Button("Back to Main");
//		SuperStage.designBtn(790, 540, backbtn);

		exitbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				System.exit(0);
			}
		});

//		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			public void handle(MouseEvent arg0) {
//				TitleScreen ts = new TitleScreen();
//				ts.setStage(stage);
//			}
//		});
//		this.root.getChildren().addAll(canvas, exitbtn, backbtn);

		this.root.getChildren().addAll(canvas, exitbtn);
	}

	Scene getScene(){
		return this.scene;
	}
}
