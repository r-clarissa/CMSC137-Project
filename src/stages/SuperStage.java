package stages;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class SuperStage {

	public static final Color blue = Color.rgb(42, 207, 224);
	public static final Color red = Color.rgb(255, 85, 79);
	public static final Color yellow = Color.rgb(255, 214, 129);

	public static final int WINDOW_HEIGHT = 700;
	public static final int WINDOW_WIDTH = 1200;

	public static final int SELECT_TOKEN_HEIGHT = 224;
	public static final int SELECT_TOKEN_WIDTH = 184;

	public static final int GAME_TOKEN_WIDTH = 101;
	public static final int GAME_TOKEN_HEIGHT = 123;
	public static final int GAME_AMMO_WIDTH = 60;
	public static final int GAME_AMMO_HEIGHT = 36;

	// Powerups
	public static final int GAME_ALCOHOL_SIZE = 73;
	public static final int GAME_VITAMINS_SIZE = 66;
	public static final int GAME_FACEMASK_SIZE = 55;

	// Viruses
	public static final int REGULAR_VIRUS_SIZE = 97;
	public static final int AMMO_VIRUS_SIZE = 72;
	public static final int BOSS_VIRUS_SIZE = 308;
	public static final int BOSS3_WIDTH = 181;
	public static final int BOSS3_HEIGHT = 269;

	public static final String sourceSansPro = "Source Sans Pro";

	// Stages
	public final static Image landingPage = new Image("images/Colored Landing Page.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image selectTokenPage = new Image("images/Colored Select Token.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image startCountdownPage = new Image("images/Colored Game Countdown.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image resumeCountdownPage = new Image("images/Resume Countdown.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image gameStagePage = new Image("images/Colored Game Stage.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image winnerPage = new Image("images/Colored Congrats Prompt.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image gameoverPage = new Image("images/Colored Game Over Prompt.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);

	public final static Image icon = new Image("images/Virus 1.png");

	// Tokens
	public final static Image token1 = new Image("images/Token 1.png", SELECT_TOKEN_WIDTH, SELECT_TOKEN_HEIGHT, false, false);
	public final static Image token2 = new Image("images/Token 2.png", SELECT_TOKEN_WIDTH, SELECT_TOKEN_HEIGHT, false, false);
	public final static Image token3 = new Image("images/Token 3.png", SELECT_TOKEN_WIDTH, SELECT_TOKEN_HEIGHT, false, false);
	public final static Image token4 = new Image("images/Token 4.png", SELECT_TOKEN_WIDTH, SELECT_TOKEN_HEIGHT, false, false);

	// Prompts
	public final static Image boss1Prompt = new Image("images/Boss 1 Prompt.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image boss2Prompt = new Image("images/Boss 2 Prompt.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image boss3Prompt = new Image("images/Boss 3 Prompt.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);
	public final static Image demandPrompt = new Image("images/Demand.png", WINDOW_WIDTH, WINDOW_HEIGHT, false, false);

	// Tokens in the main game and ammo
	public final static Image token1_game = new Image("images/Token 1.png", GAME_TOKEN_WIDTH, GAME_TOKEN_HEIGHT, false, false);
	public final static Image token1_ammo = new Image("images/Ammo Yellow.png", GAME_AMMO_WIDTH, GAME_AMMO_HEIGHT, false, false);

	public final static Image token2_game = new Image("images/Token 2.png", GAME_TOKEN_WIDTH, GAME_TOKEN_HEIGHT, false, false);
	public final static Image token2_ammo = new Image("images/Ammo Red.png", GAME_AMMO_WIDTH, GAME_AMMO_HEIGHT, false, false);

	public final static Image token3_game = new Image("images/Token 3.png", GAME_TOKEN_WIDTH, GAME_TOKEN_HEIGHT, false, false);
	public final static Image token3_ammo = new Image("images/Ammo Blue.png", GAME_AMMO_WIDTH, GAME_AMMO_HEIGHT, false, false);

	public final static Image token4_game = new Image("images/Token 4.png", GAME_TOKEN_WIDTH, GAME_TOKEN_HEIGHT, false, false);
	public final static Image token4_ammo = new Image("images/Ammo Grey.png", GAME_AMMO_WIDTH, GAME_AMMO_HEIGHT, false, false);

	// Powerups
	public final static Image alcohol = new Image("images/Alcohol.png", GAME_ALCOHOL_SIZE, GAME_ALCOHOL_SIZE, false, false);
	public final static Image vitamins = new Image("images/Vitamins.png", GAME_VITAMINS_SIZE, GAME_VITAMINS_SIZE, false, false);
	public final static Image facemask = new Image("images/Mask.png", GAME_FACEMASK_SIZE, GAME_FACEMASK_SIZE, false, false);

	// Viruses
	public final static Image regularVirus = new Image("images/Virus 3.png", REGULAR_VIRUS_SIZE, REGULAR_VIRUS_SIZE, false, false);
	public final static Image boss1 = new Image("images/Boss 1.png", BOSS_VIRUS_SIZE, BOSS_VIRUS_SIZE, false, false);
	public final static Image boss1_ammo = new Image("images/Boss 1.png", AMMO_VIRUS_SIZE, AMMO_VIRUS_SIZE, false, false);
	public final static Image boss2 = new Image("images/Boss 2.png", BOSS_VIRUS_SIZE, BOSS_VIRUS_SIZE, false, false);
	public final static Image boss2_ammo = new Image("images/Boss 2.png", AMMO_VIRUS_SIZE, AMMO_VIRUS_SIZE, false, false);
	public final static Image boss3 = new Image("images/Boss 3.png", BOSS3_WIDTH, BOSS3_HEIGHT, false, false);
	public final static Image boss3_ammo = new Image("images/Boss 3.png", AMMO_VIRUS_SIZE, AMMO_VIRUS_SIZE, false, false);

	// Flags for prompts
	public final static int NULL = 0;
	public final static int BOSS1 = 1;
	public final static int BOSS2 = 2;
	public final static int BOSS3 = 3;
	public final static int DEMAND = 4;


	public static void designBtn(int x, int y, Button btn ) {

		Font font = Font.font("Source Sans Pro", FontWeight.BOLD, FontPosture.REGULAR, 35);

		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetX(0);
		dropshadow.setOffsetY(4);
		dropshadow.setRadius(4);
		dropshadow.setColor(Color.rgb(255, 200, 87));

		btn.setStyle("-fx-background-color: #084C61; -fx-background-radius: 50px; -fx-background-width: 300px");
		btn.setEffect(dropshadow);
		btn.setPrefSize(300, 77);
		btn.setFont(font);
		btn.setLayoutX(x);
		btn.setLayoutY(y);
		btn.setTextFill(Color.rgb(255, 200, 87));
	}

	public static Button designBtn(int x, int y, int type) {

		ImageView imgView;
		String label;

		if (type==1) {imgView = new ImageView(token1); label="Token 1";}
		else if (type==2) {imgView = new ImageView(token2); label="Token 2";}
		else if (type==3) {imgView = new ImageView(token3); label="Token 3";}
		else {imgView = new ImageView(token4); label="Token 4";}

		Button btn = new Button(label, imgView);

		btn.setLayoutX(x);
		btn.setLayoutY(y);
		btn.setStyle("-fx-background-color: transparent");
		btn.setPrefSize(SELECT_TOKEN_WIDTH, SELECT_TOKEN_HEIGHT);

		return btn;
	}

}
