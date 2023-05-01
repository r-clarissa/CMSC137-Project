package gamepackage;

import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GameStage {

	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;
	public final static Image BIGGER_SHIP_IMAGE = new Image("images/Animated images/Main_Rocket.gif",400,550,true,false);

	private Scene scene;
	private Scene startScene;
	private Scene instructionScene;
	private Scene aboutScene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;
	private Thread timerThread;
	private Timer timer;
	private ImageView background;
	private ImageView bgWelcomeShip;
	private LimitedTimerBackgroundWelcomeShip bgWelcomeShipTimer;
	private Thread moveWelcomeShip;
	protected Media bgmusic;
	protected MediaPlayer player;
	protected Media bgmusic2;
	protected MediaPlayer player2;

	//the class constructor
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.gc = canvas.getGraphicsContext2D();
		this.gc=canvas.getGraphicsContext2D();
		this.background = createView(GameTimer.BACKGROUND);
		this.bgmusic = new Media(Paths.get("src/sounds/retroBg.mp3").toUri().toString());
		this.player= new MediaPlayer(bgmusic);
		this.bgmusic2 = new Media(Paths.get("src/sounds/WelcomeMusic.mp3").toUri().toString());
		this.player2= new MediaPlayer(bgmusic2);
		//this.sound= new SoundEffect();
	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;

		//set stage elements here
		this.root.getChildren().addAll(background, canvas);
		this.inStartScene();
		this.initInstruct();
		this.initAbout();
		this.player2.setCycleCount(MediaPlayer.INDEFINITE);
		this.player2.play();
		this.player2.setAutoPlay(true);
		this.stage.setTitle("Mini Ship Shooting Game");
		this.stage.setScene(this.startScene);

		this.stage.show();
	}

	private void initInstruct(){      //this will initialize the scene for instruction
		StackPane root =new StackPane();

		Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image bg = new Image("images/Background/Layered_Background_Pic_No_Overlay.jpg",7585,GameStage.WINDOW_HEIGHT,false,false);
        gc.drawImage(bg, 0, 0);
        Image ins = new Image("images/Animated images/Instructions.gif",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
        ImageView instruct = new ImageView();
        instruct.setImage(ins);

        root.getChildren().addAll(canvas,instruct,this.goBackButton());
        this.instructionScene=new Scene(root,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);

	}

	private void initAbout(){       //this will initialize the scene for iabout info
		StackPane root =new StackPane();

		Canvas canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image bg = new Image("images/Background/Layered_Background_Pic_No_Overlay.jpg",7585,GameStage.WINDOW_HEIGHT,false,false);
        gc.drawImage(bg, 0, 0);
        Image ins = new Image("images/Animated images/about.gif",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
        ImageView instruct=new ImageView();
        instruct.setImage(ins);

        VBox goback= this.goBackbtnAbout();
        goback.setLayoutX(740);
        goback.setLayoutY(-50);

        root.getChildren().addAll(canvas,instruct,this.goBackbtnAbout());
        this.aboutScene=new Scene(root,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
	}

	private void inStartScene (){ //this will initialize the scene for welcome scene
		Group root = new Group();
        Image bg = new Image("images/Animated images/Welcome.gif",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);

        ImageView welcome= new ImageView();
        welcome.setImage(bg);

        VBox vboxControls = Button();
        vboxControls.setLayoutX(340);
        vboxControls.setLayoutY(-50);

        // make new welcome ship
        this.bgWelcomeShip = new ImageView();
        this.bgWelcomeShip.setImage(BIGGER_SHIP_IMAGE);
        this.bgWelcomeShip.setLayoutX(110);
        this.bgWelcomeShip.setLayoutY(220);

        // move the welcome-scene elements
        this.moveWelcomeShip = new Thread(this.bgWelcomeShipTimer = new LimitedTimerBackgroundWelcomeShip(this.bgWelcomeShip));
        this.moveWelcomeShip.start();

        root.getChildren().addAll(welcome, this.bgWelcomeShip,vboxControls);
        this.startScene=new Scene(root,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
	}


	private VBox Button(){
		VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setTranslateX(269);
        vbox.setTranslateY(265);
        vbox.setSpacing(10);

        Button b1 = new Button("New Game");
        //b1.setStyle("-fx-background-color: #808080;");

        Button b2 = new Button("Instuctions");
        //b2.setStyle("-fx-background-color: #808080;");

        Button b3 = new Button("About");
        //b3.setStyle("-fx-background-color: #808080;");


        vbox.getChildren().addAll(b1,b2,b3);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	setGameStage(stage,1);
            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	setGameStage(stage,2);
            }
        });

        b3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	setGameStage(stage,4);
            }
        });

        return vbox;
    }

	private VBox goBackButton(){

		VBox vbox = new VBox();
        vbox.setAlignment(Pos.BASELINE_LEFT);
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(8);

        Button b1 = new Button("Main Menu");
        //b1.setStyle("-fx-background-color: #808080;");


        vbox.getChildren().add(b1);

        b1.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
            	setGameStage(stage,3);

            }
        });
        return vbox;


	}
	private VBox goBackbtnAbout(){

		VBox vbox = new VBox();
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setPadding(new Insets(250));
        vbox.setTranslateX(200);
        vbox.setTranslateY(210);
        vbox.setSpacing(8);

        Button b1 = new Button("Main Menu");
        //b1.setStyle("-fx-background-color: #808080;");


        vbox.getChildren().add(b1);

        b1.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
            	setGameStage(stage,3);

            }
        });
        return vbox;


	}

	void setGameStage(Stage stage, int val){  //upon pressing buttons

		if(val==1){

			this.player2.stop();
			stage.setScene(this.scene);    // changes the scene into the game scene
			this.interruptWelcomeShipRendering();

			// Create a new timer and then start that timer
			timer = new Timer();
			timerThread = new Thread(timer);
			this.player.setCycleCount(MediaPlayer.INDEFINITE);
    		this.player.play();
    		this.player.setAutoPlay(true);

			//instantiate an animation timer; also pass the thread of timer
			this.gametimer = new GameTimer(this.gc,this.scene, timerThread, timer, background);this.gametimer.start();
			timerThread.start();
			stage.show();
			this.gametimer.setGameOver(this);

		}else if(val==2){

			stage.setScene(this.instructionScene); //changes the scene into the instruction scene
			stage.show();

		}else if(val==3){

			this.player.stop();
			this.player2.play();
			this.inStartScene();
			stage.setScene(this.startScene);  //changes the scene back into the welcome scene
			stage.show();

		}else if(val==4){

			stage.setScene(this.aboutScene);  //changes the scene into avout info scene
			stage.show();

		}
	}


	private ImageView createView(Image image) {

		ImageView view = new ImageView();
		view.setImage(image);

		return view;
	}

	Stage getStage(){
		return(this.stage);
	}

	private void interruptWelcomeShipRendering() {
		this.bgWelcomeShipTimer.stopRender();
	}
}

