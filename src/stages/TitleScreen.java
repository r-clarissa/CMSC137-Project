package stages;
import stages.GameStage;
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

public class TitleScreen{
	private Group root;
	private Scene scene;

	private GraphicsContext gc;
	private Canvas canvas;

	private Image hospital = new Image ("images/Hospital.png", 525, 510, false, false);
	private Image facemask = new Image ("images/Facemask.png", 150, 70, false, false);
	private Image boss = new Image ("images/Boss.png", 270, 270, false, false);
	private Image alcohol = new Image ("images/Alcohol.png", 60, 100, false, false);
	private Image vaccine = new Image ("images/Vaccine.png", 125, 35, false, false);
	private Image vitamins = new Image ("images/Vitamins.png", 100, 110, false, false);
	private Image virus1 = new Image ("images/Virus.png", 50, 50, false, false);
	private Image virus2 = new Image ("images/Virus.png", 100, 100, false, false);
	private Image virus3 = new Image ("images/Virus.png", 75, 75, false, false);

//Image background;

	public TitleScreen(){
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(800,500);
		this.gc = canvas.getGraphicsContext2D();
	}

	public void setStage(Stage stage){
		// TODO Auto-generated method stub
		gc.clearRect(0, 0, 800, 500);
		gc.drawImage(GameStage.bg, 0, 0);

		//"THE" Text
		Font theFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 70);
		String the = "THE ";
		int xThe = 50, yThe = 130;

		showText(xThe, yThe, theFont, the, 1);

		//"PANDEMIC" Text
		Font pandemicFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 100);
		String pandemic = "PANDEMIC ";
		int xPandemic = 180, yPandemic = 130;

		showText(xPandemic, yPandemic, pandemicFont, pandemic, 1);

		//"GAME" Text
		Font gameFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 70);
		String game = "GAME";
		int xGame = 470, yGame = 210;

		showText(xGame, yGame, gameFont, game, 2);

		this.gc.drawImage(hospital, -150, 180);
		this.gc.drawImage(facemask, 10, 220);
		this.gc.drawImage(boss, 650, -100);
		this.gc.drawImage(alcohol, 175, 225);
		this.gc.drawImage(vaccine, 255, 290);
		this.gc.drawImage(vitamins, 360, 375);
		this.gc.drawImage(virus1, 600, 80);
		this.gc.drawImage(virus2, 640, 160);
		this.gc.drawImage(virus3, 750, 200);

		Button newgamebtn = new Button("NEW GAME");
		Button instructionbtn = new Button("INSTRUCTION");
		Button aboutbtn = new Button("ABOUT");

		Font btnFont = Font.font("Impact",FontWeight.NORMAL, FontPosture.REGULAR, 50);

		designButton(475, 245, btnFont, newgamebtn, 1);
		designButton(420, 310, btnFont, instructionbtn, 2);
		designButton(550, 375, btnFont, aboutbtn, 1);

		newgamebtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				GameStage newgamestage = new GameStage();
				newgamestage.setStage(stage);
			}
		});

		instructionbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				Instruction is = new Instruction();
				is.setStage(stage);
			}
		});


		aboutbtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				About aboutstage = new About();
				aboutstage.setStage(stage);
			}
		});

		this.root.getChildren().addAll(canvas, newgamebtn, instructionbtn, aboutbtn);

		stage.setTitle("The Pandemic Game");
		stage.setScene(this.scene);
		stage.getIcons().add(GameStage.icon);
		stage.show();
	}


	// This is called in setStage
	private void showText(int x, int y, Font font, String toPrint, int color) {

		this.gc.setFont(font);							// This sets the font of the text.
		this.gc.setStroke(Color.BLACK);					// This sets the font border color equal to black
		this.gc.setLineWidth(3.0);						// This sets the font border width

		// Sets the color of the font
		if (color == 1) this.gc.setFill(Color.rgb(245, 136, 0));
		else this.gc.setFill(Color.rgb(248, 188, 36));

		this.gc.fillText(toPrint, x, y);
		this.gc.strokeText(toPrint, x, y);
	}


	private void designButton(int x, int y, Font font, Button button, int color) {

		button.setStyle("-fx-background-color: transparent;");
		button.setFont(font);
		button.setLayoutX(x);
		button.setLayoutY(y);
		if (color == 1) button.setTextFill(Color.rgb(245, 136, 0));
		else button.setTextFill(Color.rgb(248, 188, 36));
	}

}
