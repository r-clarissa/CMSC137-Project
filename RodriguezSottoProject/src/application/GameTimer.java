package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameTimer extends AnimationTimer{

	private GraphicsContext gc;
	private Scene theScene;
	private GameStage gameStage;
	private Shooter shooter;
	private ArrayList<Zombie> zombies; 	  //ArrayList of Zombies
	private ArrayList<PowerUps> powerups; //ArrayList of PowerUps

	private double spawnTime = System.nanoTime();
	private int previousSecond = -1;

	public static final int INITIAL_X = 150;
	public static final int INITIAL_Y = 250;

	public static final int MAX_INGAME_SPAWN = 3;
	public static final double SPAWN_DELAY = 5.0d;
	public static final int MAX_NUM_ENEMIES = 7;

	public static final double POWERUP_SPAWN_TIME = 10.0d; // power up
	public static final double BOSS_ZOMBIE_SPAWN_TIME = 30.0d; // boss

	public final Image bg = new Image("images/bgfinal1.png",GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT,false,false);

	GameTimer(GraphicsContext gc, Scene theScene, GameStage gameStage){
		this.gc = gc;
		this.theScene = theScene;
		this.gameStage = gameStage;
		this.shooter = new Shooter("Battle Agent",GameTimer.INITIAL_X,GameTimer.INITIAL_Y); //initialize a new shooter
		this.zombies = new ArrayList<Zombie>(); //initialize an arraylist of zombies
		this.powerups = new ArrayList<PowerUps>(); //initialize an arraylist of powerups
		this.startSpawnZombies();
		this.handleKeyPressEvent();
	}

	@Override
	public void handle(long currentNanoTime) {
		double spawnElapsedTime = (currentNanoTime - this.spawnTime)/1000000000.0;

		//spawns the zombie, power ups, and boss zombie
		if(previousSecond != (int)spawnElapsedTime && (int)spawnElapsedTime != 0) {
			if((int)(spawnElapsedTime%GameTimer.SPAWN_DELAY) == 0) {
				this.ingameSpawnZombies();
			}
			if((int)spawnElapsedTime == (int)GameTimer.BOSS_ZOMBIE_SPAWN_TIME) {
				this.spawnBossZombie();
			}
			if((int)spawnElapsedTime%GameTimer.POWERUP_SPAWN_TIME == 0) {
				this.spawnPowerUps();
			}

			this.despawnPowerUps();

			if(this.shooter.isInvincible()) {
				this.shooter.setInvincibilityElapsed();
			}
		}

		//clears the canvas
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);

		//draws the background
		this.gc.drawImage(this.bg, 0, 0);

		//move the elements
		this.shooter.move();
		this.moveBullets();
		this.moveZombies();
		this.checkPowerUpsCollision();

		//render the elements
		this.shooter.render(this.gc);
		this.renderBullets();
		this.renderZombies();
		this.renderPowerUps();

		this.checkShooter(spawnElapsedTime);
		this.drawScore(61-spawnElapsedTime); //61 was added to make it a countdown timer

		// added for spawned time
		if(this.previousSecond != (int)spawnElapsedTime) {
			this.previousSecond = (int)spawnElapsedTime;
		}
	}

	//method for showing the status
	private void drawScore(double t) {
		int time = (int) t; // typecasting a double to int

		//time
		this.gc.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
		this.gc.setFill(Color.CRIMSON);
		this.gc.fillText("Time: ", 330, 50);
		this.gc.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
		this.gc.setFill(Color.CRIMSON);
		if(time <= 9) {
			this.gc.fillText("00:0" + time, 405, 50);
		} else if(time >= 60) {
			this.gc.fillText("01:00", 405, 50);
		} else {
			this.gc.fillText("00:" + time, 405, 50);
		}

		//score
		this.gc.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
		this.gc.setFill(Color.CRIMSON);
		this.gc.fillText("Score: ", 485, 50);
		this.gc.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
		this.gc.setFill(Color.CRIMSON);
		this.gc.fillText(this.shooter.getScore() + "", 560, 50);

		//strength
		this.gc.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
		this.gc.setFill(Color.CRIMSON);
		this.gc.fillText("Strength: ", 610, 50);
		this.gc.setFont(Font.font("Impact", FontWeight.NORMAL, 20));
		this.gc.setFill(Color.CRIMSON);
		int strength = this.shooter.getStrength();
		if(strength <= 0) {
			this.gc.fillText("0", 710, 50);
		} else {
			this.gc.fillText(strength + "", 710, 50);
		}
	}

	// method that will show the game over screen
	private void checkShooter(double t) {
		int time = (int) t; // typecasting a double to int

		if(!this.shooter.isAlive()) { // loser
			this.gameStage.flashGameOver(0, this.shooter.getScore());
			this.stop();
		} else if(time >= 60) { // winner
			this.gameStage.flashGameOver(1, this.shooter.getScore());
			this.stop();
		}
	}

	//method for rendering zombies
	private void renderZombies() {
		for (Zombie z : this.zombies){
			z.render(this.gc);
		}
	}

	//method for rendering bullets
	private void renderBullets() {
		for(Bullet b : this.shooter.getBullets()) {
			b.render(this.gc);
		}
	}

	//method for rendering power ups
	private void renderPowerUps() {
		for(PowerUps p: this.powerups) {
			p.render(this.gc);
		}
	}

	//method for spawning zombies in game
	private void ingameSpawnZombies() {
		Random r = new Random();
		for(int i=0;i<GameTimer.MAX_INGAME_SPAWN;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH/2)+(GameStage.WINDOW_WIDTH/2); // location is at greater half of screen (??)
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-Zombie.ZOMBIE_HEIGHT);
			Zombie z = new Zombie(x, y, 0); // mini zombie - 0
			this.zombies.add(z);
		}
	}

	//method for spawning zombies at the start of the game
	private void startSpawnZombies(){
		Random r = new Random();
		for(int i=0;i<GameTimer.MAX_NUM_ENEMIES;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH/2)+(GameStage.WINDOW_WIDTH/2);
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-Zombie.ZOMBIE_HEIGHT);
			Zombie z = new Zombie(x, y, 0);
			this.zombies.add(z);
		}
	}

	//method for spawning power ups
	private void spawnPowerUps() {
		PowerUps newPowerUp;
		Random r = new Random();
		int x = r.nextInt(GameStage.WINDOW_WIDTH/2); // location is at lesser half of screen
		int y = r.nextInt(GameStage.WINDOW_HEIGHT-PowerUps.POWERUP_HEIGHT);

		int type = r.nextInt(2);

		switch(type) {
			case 0: newPowerUp = new EnergyDrink(x, y); break;
			default: newPowerUp = new LoveBomb(x, y); break;
		}

		this.powerups.add(newPowerUp);
	}

	//method for despawning for power ups
	private void despawnPowerUps() {
		for(int i=0; i<this.powerups.size(); i++) {
			PowerUps p = this.powerups.get(i);
			p.setAvailabilityTimeElapsed();
			if(!p.isAvailable()) {
				this.powerups.remove(i);
			}
		}
	}

	//method that checks whether the power ups collide with the shooter
	private void checkPowerUpsCollision() {
		for(int i=0; i<this.powerups.size(); i++) {
			PowerUps p = this.powerups.get(i);
			if(p.isAvailable()) {
				p.checkCollision(this.shooter);
			}else {
				this.powerups.remove(i);
			}
		}
	}

	//method for spawning boss zombie
	private void spawnBossZombie() { //add boss at 30 seconds
		Random r = new Random();
		int x = GameStage.WINDOW_WIDTH-Zombie.BOSS_ZOMBIE_SIZE; //spawns on the right side
		int y = r.nextInt(GameStage.WINDOW_HEIGHT-Zombie.BOSS_ZOMBIE_SIZE);
		this.zombies.add(new Zombie(x, y, 1));
	}

	//method for moving the bullets in the arraylist of bullets
	private void moveBullets(){
		ArrayList<Bullet> bList = this.shooter.getBullets();

		for(int i = 0; i < bList.size(); i++){
			Bullet b = bList.get(i);
			if(b.getVisible() == true) {
				b.move();
			} else {
				bList.remove(i);
			}
		}
	}

	//method for moving the zombies in the arraylist of zombies
	private void moveZombies(){
		for(int i = 0; i < this.zombies.size(); i++){
			Zombie z = this.zombies.get(i);
			if(z.isAlive()) {
				z.move();
				z.checkCollision(this.shooter, z.zombieType());
			} else {
				this.zombies.remove(i);
			}
		}
	}

	//method that handles every press of keys in the keyboard
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyShooter(code);
			}
		});

		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopMyShooter(code);
		            }
		        });
    }

	//method for moving the shooter
	private void moveMyShooter(KeyCode ke) {
		if(ke==KeyCode.UP) this.shooter.setDY(-10); // to change to 10

		if(ke==KeyCode.LEFT) this.shooter.setDX(-10);

		if(ke==KeyCode.DOWN) this.shooter.setDY(10);

		if(ke==KeyCode.RIGHT) this.shooter.setDX(10);

		if(ke==KeyCode.SPACE) this.shooter.shoot();

		System.out.println(ke+" key pressed.");
   	}

	private void stopMyShooter(KeyCode ke){
		this.shooter.setDX(0);
		this.shooter.setDY(0);
	}
}
