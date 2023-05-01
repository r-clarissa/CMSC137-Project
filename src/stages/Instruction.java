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
import javafx.stage.Stage;

public class Instruction {
	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	Instruction(){
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(800,500);
		this.gc = canvas.getGraphicsContext2D();
	}


	void setStage(Stage stage){
		gc.clearRect(0, 0, 800, 500);
		gc.drawImage(GameStage.bg, 0, 0);

		//Instruction Title
		Font instructionsTitleFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 50);

		String instructions = "INSTRUCTIONS ";
		showText(30, 60, instructionsTitleFont, instructions, 2);

		//Show Text Instructions
		Font contentFonts = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 28);

		String line1 = "1.  The game objective is to survive the attacks of virus until the ";
		showText(30, 95, contentFonts, line1, 1);

		String line2 = "     end of time (1 minute).";
		showText(30, 125, contentFonts, line2, 1);

		String line3 = "2. Use UP, RIGHT, DOWN, LEFT button of your device to navigate. ";
		showText(30, 165, contentFonts, line3, 1);

		String line4 = "3. Press SPACE to shoot the viruses.";
		showText(30, 205, contentFonts, line4, 1);

		String line5 = "4. The player will be aided with power-ups:";
		showText(30, 245, contentFonts, line5, 1);

		String line6 = "   a. Facemask – adds 50 to strength";
		showText(60, 275, contentFonts, line6, 1);

		String line7 = "   b. Vitamins – makes player immortal for 5 seconds";
		showText(60, 305, contentFonts, line7, 1);

		String line8 = "   c. Alcohol – 100 increase in strength when hitting the ";
		showText(60, 335, contentFonts, line8, 1);

		String line9 = "        boss, if no boss is present, adds 50 to strength.";
		showText(60, 365, contentFonts, line9, 1);

		String line10 = "5. The player loses if all the strength is exhausted before the end";
		showText(30, 405, contentFonts, line10, 1);

		String line11 = "     of time.";
		showText(30, 435, contentFonts, line11, 1);


		//BACK BUTTON
		Font btnFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 35);

		Button backbtn = new Button("BACK");
		designButton(550, 425, backbtn, btnFont, 1);

		Button nextbtn = new Button("NEXT");
		designButton(675, 425, nextbtn, btnFont, 2);



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
		stage.setTitle("The Pandemic Game - Instructions");
		stage.getIcons().add(GameStage.icon);
		stage.setScene(this.scene);
		stage.show();
	}

	private void showText(int x, int y, Font font, String toPrint, int color) {

		this.gc.setFont(font);							// This sets the font of the text.
		this.gc.setStroke(Color.BLACK);					// This sets the font border color equal to black

		// Sets the color of the font
		if (color == 1) {
			this.gc.setFill(Color.rgb(245, 136, 0));
			this.gc.setLineWidth(1.0);						// This sets the font border width
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
}
