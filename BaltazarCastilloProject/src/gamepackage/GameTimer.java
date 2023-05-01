package gamepackage;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method.
 */

public class GameTimer extends AnimationTimer{

	public static final int MAX_NUM_ENEMY_SPAWN = 3;
	public static final int REQUIRED_INITIAL_ENEMY_SPAWN = 7;
	public final static Image BACKGROUND = new Image("images/Background/Layered_Background_Pic.jpg",7585,GameStage.WINDOW_HEIGHT,false,false);

	private final static double SPAWN_DELAY = 5;
	private final static double POWERUP_SPAWN_DELAY = 10;
	private final static double BOSS_SPAWN_DELAY=30;

	private long startSpawn;
	private long powerupSpawn;
	private long bossSpawn;
	private GraphicsContext gc;
	private GameStage st;
	private Scene theScene;
	private Ship myShip;
	private BossEnemy Bossing;
	private ArrayList<SmallerEnemy> Enemies;
	private ArrayList<PowerUp> powerups;
	private Thread timerThread;
	private Timer timer;

	// for the counter of background image position
	private int currentBgPositionX;
	private ImageView backgroundView;

	// variable to temporarily stop the gameTimer's consistent operations in handle()
	private boolean onPlayMode = true;

	GameTimer(GraphicsContext gc, Scene theScene, Thread timerThread, Timer timer, ImageView backgroundView){
		this.gc = gc;
		this.theScene = theScene;
		this.myShip = new Ship("Going merry",100,100);
		this.Bossing=new BossEnemy(700,200);
		this.startSpawn = System.nanoTime();
		this.powerupSpawn = System.nanoTime();
		this.bossSpawn = System.nanoTime();
		this.timerThread= timerThread;
		this.timer = timer;
		this.currentBgPositionX = 0;
		this.backgroundView = backgroundView;

		//instantiate the ArrayList of SmallerEnemy
		this.Enemies = new ArrayList<SmallerEnemy>();

		//instantiate the ArrayList of Power Ups
		this.powerups = new ArrayList<PowerUp>();

		//call the spawnEnemies method
		this.spawnEnemies();

		// create initial count of Enemies (make 4 Enemies only. Other 3 is from spawnEnemies method)
		this.spawnInitialEnemies();

		//call method to handle mouse click event
		this.handleKeyPressEvent();
	}

	@Override
	public void handle(long currentNanoTime) {

		// while the game is running; ends when play=false, e.g. game finished.
		// Can be run again by making play=true.
		if (onPlayMode) {

			// Checks if the ship is already dead or not. If dead, stop timer
			if (this.myShip.isAlive()) {

				// update background position
				this.backgroundPositionUpdate();

				// for time
				double BossElapsedTime = ((currentNanoTime - this.bossSpawn) / 1000000000.0);
				double spawnElapsedTime = ((currentNanoTime - this.startSpawn) / 1000000000.0);
				double powerupElapsedTime = ((currentNanoTime - this.powerupSpawn) / 1000000000.0);

//				double powerupSummonTimeCounter = (currentNanoTime - this.powerupRemove) / 1000000000.0;

				this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);


				// move and render the ship
				this.myShip.move();
				this.myShip.render(this.gc);

				// eto yung nagsspawn ng Enemies every 5 secs
				if(spawnElapsedTime > GameTimer.SPAWN_DELAY) {
		        	this.spawnEnemies();
		        	this.startSpawn = System.nanoTime();
		        }

				// eto yung nagsspawn ng Enemies every 5 secs
				if(powerupElapsedTime > GameTimer.POWERUP_SPAWN_DELAY) {
		        	this.spawnPowerups();
		        	this.powerupSpawn = System.nanoTime();
		        }

				// eto naman yung delay para sa boss
				if (BossElapsedTime>=GameTimer.BOSS_SPAWN_DELAY){
					this.Bossing.render(this.gc);
					this.moveBoss();
				}

				// Call the moveBullets and moveEnemies methods, and check potential powerup impact
				this.moveBullets();
				this.moveEnemies();
				this.powerupImpactCheck();

				// Call the renderEnemies and renderBullets methods, and powerups too
				this.renderBullets();
				this.renderEnemies();
				this.renderPowerups();
				this.displayScore();
				this.displayStrength();
				this.displayTime();

				if (timer.isFinished()) {
					this.onPlayMode = false;
					this.endGame();
				}

			} else {
				this.onPlayMode = false;
				this.endGame();
			}
		}
	}

	private void backgroundPositionUpdate() {
		currentBgPositionX--;
		this.backgroundView.setLayoutX(currentBgPositionX);
	}



	private void displayScore(){
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText("Score:", 680, 30);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText(this.myShip.getScore()+"", 755, 32);
	}


	private void displayStrength(){

		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText("HP:", 20, 30);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText(this.myShip.getStrength()+"", 70, 32);

	}

	private void displayTime(){

		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText("Time:", 310, 32);
		this.gc.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
		this.gc.setFill(Color.BLACK);
		this.gc.fillText(this.timer.getSecondsLeft()+"", 380, 32);

	}


	//method that will render/draw the Enemies to the canvas
	private void renderEnemies() {
		for (SmallerEnemy f : this.Enemies){
			f.render(this.gc);
		}
	}

	//method that will render/draw the powerups to the canvas
	private void renderPowerups() {
		for (PowerUp powerup: this.powerups){
			powerup.render(this.gc);
		}
	}

	//method that will render/draw the bullets to the canvas
	private void renderBullets() {

		// Loop through the bullets arraylist of myShip and render each bullet to the canvas
		ArrayList<Bullet> bulletsList = this.myShip.getBullets();
		for (int i=0; i<bulletsList.size(); i++) {
			Bullet bullet = bulletsList.get(i);
			bullet.render(this.gc);
		}
	}

	//method that will spawn/instantiate three Enemies at a random x,y location
	private void spawnEnemies(){

		Random r = new Random();
		for(int i=0;i<GameTimer.MAX_NUM_ENEMY_SPAWN;i++){		// Create 3 new Enemies
			int y = 60 + r.nextInt(GameStage.WINDOW_HEIGHT-(int)SmallerEnemy.SMALLER_ENEMY_IMAGE.getHeight()-60);
			int x = r.nextInt(GameStage.WINDOW_WIDTH/2)+(GameStage.WINDOW_WIDTH/2);

			// Add a new object SmallerEnemy to the Enemies arraylist
			SmallerEnemy newFish = new SmallerEnemy(x,y);
			Enemies.add(newFish);
		}
//		SmallerEnemy newFish2 = new SmallerEnemy(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT-42);
//		Enemies.add(newFish2);

	}


	//method that will spawn/instantiate powerups
	private void spawnPowerups(){
		Random r = new Random();
		int x = r.nextInt(GameStage.WINDOW_WIDTH/2);
		int y = 60 + r.nextInt(GameStage.WINDOW_HEIGHT-(int)SmallerEnemy.SMALLER_ENEMY_IMAGE.getHeight()-60);

		Random r2 = new Random();
		int a = r2.nextInt(GameStage.WINDOW_WIDTH/2);
		int b = 60 + r2.nextInt(GameStage.WINDOW_HEIGHT-(int)SmallerEnemy.SMALLER_ENEMY_IMAGE.getHeight()-60);




		// Add a new object Powerup to the powerups arraylist
		Hp hp= new Hp(x,y, this.myShip,PowerUp.POWERUP_HP);
		powerups.add(hp);

		Shield shield= new Shield(a,b, this.myShip,PowerUp.POWERUP_INVISIBILITY);
		powerups.add(shield);
	}

	// create initial count of Enemies (make 4 Enemies only. Other 3 is from spawnEnemies method)
	private void spawnInitialEnemies(){
		Random r = new Random();
		for(int i=0;i<(GameTimer.REQUIRED_INITIAL_ENEMY_SPAWN-GameTimer.MAX_NUM_ENEMY_SPAWN);i++){		// Create 3 new Enemies
			int y = 60 + r.nextInt(GameStage.WINDOW_HEIGHT-(int)SmallerEnemy.SMALLER_ENEMY_IMAGE.getHeight()-60);
			int x = r.nextInt(GameStage.WINDOW_WIDTH/2)+(GameStage.WINDOW_WIDTH/2);

			// Add a new object SmallerEnemy to the Enemies arraylist
			SmallerEnemy newFish = new SmallerEnemy(x,y);
			Enemies.add(newFish);
		}
	}

	//method that will move the bullets shot by a ship
	private void moveBullets(){

		//create a local arraylist of Bullets for the bullets 'shot' by the ship
		ArrayList<Bullet> bList = this.myShip.getBullets();

		//Loop through the bullet list and check whether a bullet is still visible.
		for(int i = 0; i < bList.size(); i++){
			Bullet b = bList.get(i);

			// If a bullet is visible, move the bullet, else, remove the bullet from the bullet array list.
			if (b.isVisible()) {
				b.move();
			} else {
				bList.remove(i);
				b.renderBoom(this.gc, b.getX(), b.getY());
			}
		}
	}

	//method that will move the Enemies
	private void moveEnemies(){

		//Loop through the Enemies arraylist
		for(int i = 0; i < this.Enemies.size(); i++){
			SmallerEnemy f = this.Enemies.get(i);

			//If a SmallerEnemy is alive, move the SmallerEnemy. Else, remove the SmallerEnemy from the Enemies arraylist.
			if (f.getVisible()) {
				f.move();
				f.checkCollision(this.myShip);
			} else {
				this.Enemies.remove(i);
				f.renderBoom(this.gc, f.getX(), f.getY());
			}
		}
	}

	//method that will move the Enemies
	private void powerupImpactCheck(){

		//Loop through the powerups arraylist
		for(int i = 0; i < this.powerups.size(); i++){
			PowerUp powerup = this.powerups.get(i);

			//If a powerup is visible, check collision. Else, remove from list.
			if (powerup.getVisible()) {
				powerup.checkCollision(this.myShip);
			} else {
				this.powerups.remove(i);
			}
		}
	}

	private void moveBoss(){

		//Loop through the Enemies arraylist
		//If a SmallerEnemy is alive, move the SmallerEnemy. Else, remove the SmallerEnemy from the Enemies arraylist.
		if (Bossing.getVisible()) {
			Bossing.move();
			Bossing.checkCollision(this.myShip);
		} else {
			this.Bossing.renderBoom(this.gc, this.Bossing.getX(), this.Bossing.getY());
			this.Bossing.boomVisible=false;
		}
	}

	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyShip(code);
			}
		});

		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		    public void handle(KeyEvent e){
		      	KeyCode code = e.getCode();
		        stopMyShip(code);
		    }
		});
    }

	//method that will move the ship depending on the key pressed
	private void moveMyShip(KeyCode ke) {

	//  DIRECTION-ARRAY FORMTAT: [left, right, down, up]
		int[] directionArray = new int[4];

		if(ke==KeyCode.UP) 		{this.myShip.setDY(-Ship.SHIP_MAX_SPEED);	directionArray[3] = -Ship.SHIP_MAX_SPEED;}
		if(ke==KeyCode.LEFT) 	{this.myShip.setDX(-Ship.SHIP_MAX_SPEED);	directionArray[0] = -Ship.SHIP_MAX_SPEED;}
		if(ke==KeyCode.DOWN) 	{this.myShip.setDY(Ship.SHIP_MAX_SPEED);	directionArray[2] = Ship.SHIP_MAX_SPEED;}
		if(ke==KeyCode.RIGHT) 	{this.myShip.setDX(Ship.SHIP_MAX_SPEED);	directionArray[1] = Ship.SHIP_MAX_SPEED;}
		if(ke==KeyCode.SPACE) 	this.myShip.shoot();

		this.myShip.updateDirection(directionArray);
   	}

	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopMyShip(KeyCode ke){
		this.myShip.setDX(0);
		this.myShip.setDY(0);

		//  DIRECTION-ARRAY FORMTAT: [left, right, down, up]
		int[] directionArray = new int[4];

		if(ke==KeyCode.UP) 		{directionArray[3] = Ship.SHIP_MAX_SPEED;}
		if(ke==KeyCode.LEFT) 	{directionArray[0] = Ship.SHIP_MAX_SPEED;}
		if(ke==KeyCode.DOWN) 	{directionArray[2] = -Ship.SHIP_MAX_SPEED;}
		if(ke==KeyCode.RIGHT) 	{directionArray[1] = -Ship.SHIP_MAX_SPEED;}

		this.myShip.updateDirection(directionArray);
	}


	// This will happen when the game ends.
	private void endGame() {
		this.st.player.stop();
		this.timerThread.interrupt();
		this.timer.endTimer();
		Group rooT =new Group();
		Scene endScene = new Scene(rooT,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		Canvas gameOverCanvas= new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		GraphicsContext gcOver= gameOverCanvas.getGraphicsContext2D();

        gcOver.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		gcOver.setFill(Color.WHITE);



        VBox vbox= new VBox();

        Button b5= new Button("Go back to Main Menu");
        //b5.setStyle("-fx-background-color: #808080;");
        vbox.setPadding(new Insets(310));
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.getChildren().add(b5);

        b5.setOnAction(new EventHandler<ActionEvent>() {


			public void handle(ActionEvent e) {
            	st.setGameStage(st.getStage(), 3);

            }
        });

		if (this.myShip.isAlive()) {
			Image bg = new Image("images/Non-animated images/Win.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	        gcOver.drawImage(bg, 0, 0);
			gcOver.fillText("Congratulations!", 180, 200);
			gcOver.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
			gcOver.setFill(Color.WHITE);
			gcOver.fillText("You Win!", 300, 250);

			gcOver.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			gcOver.setFill(Color.WHITE);
			gcOver.fillText("Score:", 335, 300);
			gcOver.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
			gcOver.setFill(Color.WHITE);
			gcOver.fillText(this.myShip.getScore()+"", 415, 300);
		} else {
			Image bg = new Image("images/Non-animated images/lose.png",GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,false,false);
	        gcOver.drawImage(bg, 0, 0);
			gcOver.fillText("Game Over!", 250, 200);
			gcOver.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
			gcOver.setFill(Color.WHITE);
			gcOver.fillText("You lost!", 300, 250);

			gcOver.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			gcOver.setFill(Color.WHITE);
			gcOver.fillText("Score:", 335, 300);
			gcOver.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
			gcOver.setFill(Color.WHITE);
			gcOver.fillText(this.myShip.getScore()+"", 415, 300);
		}
		rooT.getChildren().add(gameOverCanvas);
		rooT.getChildren().add(vbox);
		this.st.getStage().setScene(endScene);
		this.st.getStage().show();

	}

	public Ship getShip(){
		return this.myShip;
	}

	public void setGameOver(GameStage stage){
		this.st=stage;
	}

}
