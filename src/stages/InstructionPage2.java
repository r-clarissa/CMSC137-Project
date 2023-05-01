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

public class InstructionPage2 {
	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	private Image doctor = new Image ("images/StaffOfHermes.png", 150, 150, false, false);
	private Image facemask = new Image ("images/Facemask.png", 170, 70, false, false);
	private Image boss = new Image ("images/Boss.png", 160, 160, false, false);
	private Image alcohol = new Image ("images/Alcohol.png", 60, 100, false, false);
	private Image vaccine = new Image ("images/Vaccine.png", 125, 35, false, false);
	private Image vitamins = new Image ("images/Vitamins.png", 100, 110, false, false);
	private Image virus = new Image("images/Virus.png",70,70,false,false);

	InstructionPage2() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(800,500);
		this.gc = canvas.getGraphicsContext2D();
	}


	void setStage(Stage stage){
		gc.clearRect(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		gc.drawImage(GameStage.bg, 0, 0);

		//Instruction Title
		Font instructionsTitleFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 50);

		String instructions = "INSTRUCTIONS ";
		showText(30, 60, instructionsTitleFont, instructions, 2);

		//Show Text Instructions
		Font contentFonts = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 35);

		gc.drawImage(doctor, 30, 70);
		showText(50, 270, contentFonts, "PLAYER", 1);

		gc.drawImage(vaccine, 220, 110);
		gc.drawImage(vaccine, 240, 150);
		gc.drawImage(vaccine, 220, 180);
		showText(220, 270, contentFonts, "VACCINE", 1);

		gc.drawImage(virus, 480, 80);
		gc.drawImage(virus, 480, 160);
		gc.drawImage(virus, 400, 120);
		showText(370, 270, contentFonts, "REGULAR VIRUS", 1);

		gc.drawImage(boss, 600, 70);
		showText(640, 270, contentFonts, "BOSS", 1);

		gc.drawImage(facemask, 150, 280);
		gc.drawImage(facemask, 100, 310);
		showText(130, 430, contentFonts, "FACEMASK", 1);

		gc.drawImage(vitamins, 350, 280);
		showText(330, 430, contentFonts, "VITAMINS", 1);

		gc.drawImage(alcohol, 550, 290);
		showText(520, 430, contentFonts, "ALCOHOL", 1);

		//BACK BUTTON
		Font btnFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 35);

		Button backbtn = new Button("BACK TO MAIN");
		designButton(550, 425, backbtn, btnFont, 1);

		backbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				TitleScreen ts = new TitleScreen();
				ts.setStage(stage);
			}
		});


		this.root.getChildren().addAll(canvas, backbtn);
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
			this.gc.setLineWidth(1.5);						// This sets the font border width
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
		btn.setLayoutX(x);	//Set X
		btn.setLayoutY(y);	//Set Y

		if (color == 1) btn.setTextFill(Color.rgb(245, 136, 0));
		else btn.setTextFill(Color.rgb(248, 188, 36));
	}
}
