package stages;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ResultStage {
	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	ResultStage (int num){
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.setStage(num);

	}

	private void setStage(int outcome){
		// TODO Auto-generated method stub
		gc.clearRect(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		gc.drawImage(GameStage.bg, 0, 0);

		Font resultFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 70);
		Font buttonFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 50);

		if(outcome == GameStage.WINNER){
			showText(110, 150, resultFont, "CONGRATULATIONS! ", 1);
			showText(60, 230, resultFont, "You survived the game! ", 2);

		} else if (outcome == GameStage.LOSE){
			showText(220, 150, resultFont, "GAME OVER! ", 1);
			showText(250, 230, resultFont, "You lose! ", 2);

		}

		Button exitGameBtn = new Button("EXIT GAME");

		designButton(255, 300, exitGameBtn, buttonFont, 1);

		exitGameBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				System.exit(0);
			}
		});

		this.root.getChildren().addAll(canvas, exitGameBtn);
	}

	private void showText(int x, int y, Font font, String toPrint, int color) {

		this.gc.setFont(font);							// This sets the font of the text.
		this.gc.setStroke(Color.BLACK);					// This sets the font border color equal to black

		// Sets the color of the font
		if (color == 1) {
			this.gc.setFill(Color.rgb(245, 136, 0));
			this.gc.setLineWidth(3.0);						// This sets the font border width
		} else {
			this.gc.setFill(Color.rgb(248, 188, 36));
			this.gc.setLineWidth(3.0);						// This sets the font border width
		}

		this.gc.fillText(toPrint, x, y);
		this.gc.strokeText(toPrint, x, y);
	}

	private void designButton(int x, int y, Button btn, Font font, int color) {
		btn.setStyle("-fx-background-color: transparent;");			// Set background button to transparent
		btn.setFont(font);		//Set font style
		btn.setLayoutX(x);		//Set X
		btn.setLayoutY(y);		//Set Y

		if (color == 1) btn.setTextFill(Color.rgb(245, 136, 0));
		else btn.setTextFill(Color.rgb(248, 188, 36));
	}

	Scene getScene(){
		return this.scene;
	}
}
