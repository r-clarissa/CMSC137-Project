package game;

import java.util.ArrayList;
import java.util.Random;

// import application.Fish;
// import application.Doctor;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import stages.GameStage;

/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method.
 */

public class GameTimer extends AnimationTimer{

	private GraphicsContext gc;
	private Scene theScene;
	private Doctor myDoctor;
	private ArrayList<Virus> viruses;
	private ArrayList<PowerUps> powerups;
	private boolean gameStart = true;
	private GameStage gs;

	private static final int NUM_VIRUSES_INITIAL_START = 7;
	private static final int MAX_NUM_VIRUSES = 3;
	private boolean virusSpawning = true;
	private boolean hitByBoss = false;
	private boolean powerUpsSpawning = true;

	private long timeHit;
	private long timePowerUpsSpawn;
	private long timeVitaminsObtained;
	private long timeAlcoholObtained;
	private BigBoss boss;

	private long startmilisec = System.currentTimeMillis();

	public GameTimer(Group root, GraphicsContext gc, Scene theScene, GameStage gs){
		this.gc = gc;
		this.gs = gs;
		this.theScene = theScene;
		this.myDoctor = new Doctor("Going merry",150,250);
		//instantiate the ArrayList of Virus
		this.viruses = new ArrayList<Virus>();
		//Instantiate the ArrayList of powerups
		this.powerups = new ArrayList<PowerUps>();
		//call the spawnViruses method
		this.spawnViruses();
		//call method to handle mouse click event
		this.boss = new BigBoss(this);
		this.handleKeyPressEvent();
	}

	@Override
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH, GameStage.WINDOW_HEIGHT);
		this.myDoctor.move();

		gc.drawImage(GameStage.bg, 0, 0);

		// For time.
		long endmilisec = System.currentTimeMillis(); // source : https://www.youtube.com/watch?v=4IWgQ9jTBGM
		long time = (endmilisec - startmilisec)/1000;
		/*
		 * TODO: Call the moveBullets and moveFishes methods
		 */
		moveVaccines();
		moveViruses(time);
		if (time >= 30) moveBoss();

		collisionDoctorPowerUps(time);

		//render myDoctor
		this.myDoctor.render(this.gc);

		// Schedule to spawn a virus every 5 seconds
		if (time%5==0 && virusSpawning && time!=0) {
			this.spawnViruses();
			if (time>30 && this.boss.isAlive()) bossShootVirus();	//Shoots a larger virus every 5 seconds upon boss started moving.
			virusSpawning = false;
		} else if (time%5!=0) virusSpawning = true;

		// This line of code allows the player to be hit again 5 seconds after the time hit.
		if (time==(timeHit+5) && hitByBoss == true)
			hitByBoss = false;

		// This spawns the powerups
		if (time%10==0 && powerUpsSpawning && time!=0) {
			spawnPowerUp(PowerUps.FACEMASK_TYPE, time);
			spawnPowerUp(PowerUps.VITAMINS_TYPE, time);
			spawnPowerUp(PowerUps.ALCOHOL_TYPE, time);

			powerUpsSpawning = false;
		} else if (time%10!=0) powerUpsSpawning = true;

		// Sets the immortality of the player
		if (time==(timeVitaminsObtained+3))
			this.myDoctor.setVitaminsEffect(false);

		if (time==(timePowerUpsSpawn+5))
			hidePowerUps();
		/*
		 * TODO: Call the renderViruses and renderVaccines methods
		 */

		renderViruses();
		renderVaccines();
		renderBoss();
		renderPowerUps();

		// Display Strength, Score, Time, and boss life
		showStrength();
		showTime(time);
		showScore();
		if (this.boss.isAlive()) this.boss.showBossLife();

		// If 1:00 time has elapsed and the doctor is not alive, the player loses.
		if (time%60!=0 && !this.myDoctor.isAlive()) {
			this.stop();
			this.gs.flashResults(GameStage.LOSE);
		}

		// If 1:00 time has elapsed and the doctor is still alive, the player wins.
		if(time == 60){
			this.stop();
			this.gs.flashResults(GameStage.WINNER);
			this.startmilisec = System.currentTimeMillis();
		}

	}


	// called to hide the powerups after 5 seconds of not obtaining.
	private void hidePowerUps() {
		for (PowerUps h : this.powerups)
			h.hideImage(h, -1000, 0);
	}

	// called in handle
	// method that will render/draw the boss to the canvas
	private void renderBoss() {
		this.boss.render(this.gc);
	}

	// called in handle.
	//method that will render/draw the viruses to the canvas
	private void renderViruses() {
		for (Virus v : this.viruses){
			v.render(this.gc);
		}
	}

	private void renderPowerUps() {

		for (PowerUps h : this.powerups)
			h.render(this.gc);

	}

	// called in handle.
	//method that will render/draw the bullets to the canvas
	private void renderVaccines() {
		/*
		 *TODO: Loop through the bullets arraylist of myShip
		 *				and render each bullet to the canvas
		 */
		for (Vaccine b: this.myDoctor.getVaccines())
			b.render(this.gc);
	}

	private void spawnPowerUp(int type, long time) {
		Random r = new Random();

		int x = r.nextInt(GameStage.WINDOW_WIDTH -300) + 100;
		int y = r.nextInt(GameStage.WINDOW_HEIGHT-180) + 80;

		this.timePowerUpsSpawn = time;

		PowerUps newPowerUp = new PowerUps(x, y, type);
		powerups.add(newPowerUp);

	}

	//called in GameTimer constructor.
	//method that will spawn/instantiate three fishes at a random x,y location
	private void spawnViruses(){
		int numViruses = 0;

		if (gameStart) {
			numViruses = NUM_VIRUSES_INITIAL_START;
			this.gameStart = false;
		} else numViruses = MAX_NUM_VIRUSES;

		Random r = new Random();
		for(int i=0;i<numViruses;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH -400) + 300;
			int y = r.nextInt(GameStage.WINDOW_HEIGHT-180) + 80;
			/*
			 *TODO: Add a new object Virus to the Viruses arraylist
			 */
			Virus newVirus = new Virus(x, y, Virus.REGULAR_TYPE);
			viruses.add(newVirus);
		}

	}

	//called every handle every 8 seconds to allow boss shoot a virus
	private void bossShootVirus() {
		Virus newVirus = new Virus(this.boss.getX(), this.boss.getY(), Virus.BULLET_TYPE);
		newVirus.setSpeed(7);
		newVirus.setWidth(50);
		newVirus.setHeight(50);
		viruses.add(newVirus);
	}

	//Called to move the boss.
	private void moveBoss() {
		this.boss.move();
	}

	// called in handle.
	//method that will move the bullets shot by a ship
	private void moveVaccines(){
		//create a local arraylist of Bullets for the bullets 'shot' by the ship
		ArrayList<Vaccine> bList = this.myDoctor.getVaccines();
		//Loop through the bullet list and check whether a bullet is still visible.
		for(int i = 0; i < bList.size(); i++){
			Vaccine b = bList.get(i);
			/*
			 * TODO:  If a bullet is visible, move the bullet, else, remove the bullet from the bullet array list.
			 */
			if(b.getVisible()){ // If a bullet is visible
				b.move();

				for (Virus v: this.viruses) {

					//Call this function to check if there is a collision between the Virus and Vaccine, if there is, do necessary actions
					collisionVirusOrBossVaccine(b, v);


				}
			}
		}
	}

	// called in handle.
	//method that will move the fishes
	private void moveViruses(long time){
		//Loop through the fishes arraylist
		for(int i = 0; i < this.viruses.size(); i++){
			Virus v = this.viruses.get(i);
			/*
			 * TODO:  *If a fish is alive, move the fish. Else, remove the fish from the fishes arraylist.
			 */
			if (!v.isAlive()) viruses.remove(v);
			else {
				v.move();

				//Call this function to check if there is a collision between Doctor and Virus, if there is, do necessary actions.
				if (this.myDoctor.getVitaminsEffect() == false) collisionDoctorBossOrVirus(v, time);

			}
		}
	}

	//Called in moveVaccine
	//Call this function to determine if there is a collision between a virus and a vaccine. If there is, do necessary actions
	private void collisionVirusOrBossVaccine(Vaccine b, Virus v) {

		//Do this if there is a collision between a virus and a vaccine.
		if (b.collidesWith(v)) {
			System.out.println("COLLISION BETWEEN VIRUS AND VACCINE!");

			v.hideImage(v, 1000, 0);	//Call hideImage to hide virus image
			b.hideImage(b, -1000, 0);	//Call hideImage to hide vaccine image

			this.myDoctor.addScore(1);		//Add to score when collision between the vaccine and virus
		}

		//Do this if there is a collision between a boss and a vaccine.
		if (b.collidesWith(this.boss)) {
			System.out.println("COLLISION BETWEEN BOSS AND VACCINE!");
			this.boss.deductBossLife(this.myDoctor.getStrength());

			b.hideImage(b, -1000, 0);	////Call hideImage to hide vaccine image

			if (this.boss.getLife() <= 0) {
				this.boss.die();
				this.boss.hideImage(this.boss, -1000, 0);
			}
		}
	}

	//called in handle.
	//Call this function to check if there is a collision between the powerups and player.
	private void collisionDoctorPowerUps(long time) {

		for (int i=0; i<this.powerups.size(); i++) {
			PowerUps h = this.powerups.get(i);

			if (h.collidesWith(this.myDoctor)) {
				if (h.getType() == PowerUps.VITAMINS_TYPE) {
					this.timeVitaminsObtained = time;
					System.out.println("PowerUp: Vitamins Obtained   Time: " + time);
				} else if (h.getType() == PowerUps.ALCOHOL_TYPE) {
					this.timeAlcoholObtained = time;
					System.out.println("PowerUp: Alcohol Obtained   Strength: " + this.myDoctor.getStrength());
				} else System.out.println("PowerUp: Vitamins Obtained   Strength: " + this.myDoctor.getStrength());
				h.benefits(this.myDoctor, timeAlcoholObtained);
				h.hideImage(h, -1000, 0);

			}
		}
	}

	//Called in moveViruses
	//Call this function to determine if there is a collision between the doctor and virus. If there is, do necessary actions
	private void collisionDoctorBossOrVirus(Virus v, long time) {

		// Do this if there is a collision between a doctor and a virus.
		if (v.collidesWith(this.myDoctor)) {
			System.out.println("COLLISION BETWEEN PLAYER AND VIRUS!");

			v.hideImage(v, 1000, 0);	//Call hideImage to hide virus image

			this.myDoctor.deductStrength(Virus.STRENGTH_DEDUCTION);	//deduct from strength when collision between doctor and virus

		}

		// Do this if there is a collision between the doctor and the boss
		if (this.boss.collidesWith(this.myDoctor) && hitByBoss == false) {
			System.out.println("COLLISION BETWEEN PLAYER AND BOSS!");

			this.timeHit = time;
			this.myDoctor.deductStrength(this.boss.DAMAGE_STRENGTH);
			hitByBoss = true;
		}

		// Doctor must die if the strength is 0 or less than 0.
		if (this.myDoctor.getStrength() <= 0)
			this.myDoctor.die();
	}

	// This function is called when needs to show text on the screen
	void showText(int x, int y, String toPrint) {

		Font font = Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 40);
		this.gc.setFont(font);							// This sets the font of the text.
		this.gc.setStroke(Color.BLACK);					// This sets the font border color equal to black
		this.gc.setFill(Color.rgb(248, 188, 36));
		this.gc.setLineWidth(3.0);						// This sets the font border width
		this.gc.fillText(toPrint, x, y);
		this.gc.strokeText(toPrint, x, y);
	}

	private void showTime(long time) {

		//Integer timeInt = new Integer(this.time);
		String timeString = String.valueOf(time);		// Converts score (in integer) to string format
		if (time != 60 && time < 10) timeString = "00 : 0" + timeString;
		else if (time != 60 && time >= 10) timeString = "00 : " + timeString;
		else timeString = "01 : 00";

		showText(300, 40, "TIME: ");
		showText(425, 40, timeString);
	}

	// called in handle.
	// This function displays the Score in the screen.
	private void showScore() {

		Integer scoreInt = new Integer(this.myDoctor.getScore());
		String scoreString = scoreInt.toString();			// Converts score (in integer) to string format

		showText(575, 40, "SCORE: ");
		showText(700, 40, scoreString);

	}

	// called in handle.
	// this function shows the strength in the screen.
	private void showStrength() {

		Integer strengthInt = new Integer(this.myDoctor.getStrength());
		String strengthString = strengthInt.toString();

		showText(10, 40, "STRENGTH: ");
		showText(200, 40, strengthString);
	}


	//Called by gamestage contructor
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyDoctor(code);
			}
		});

		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopMyDoctor(code);
		            }
		        });
    }

	//called in handleKeyPressEvent in this class.
	//method that will move the ship depending on the key pressed
	private void moveMyDoctor(KeyCode ke) {
		if(ke==KeyCode.UP) this.myDoctor.setDY(-10);

		if(ke==KeyCode.LEFT) this.myDoctor.setDX(-10);

		if(ke==KeyCode.DOWN) this.myDoctor.setDY(10);

		if(ke==KeyCode.RIGHT) this.myDoctor.setDX(10);

		if(ke==KeyCode.SPACE) this.myDoctor.shoot();

		System.out.println(ke+" key pressed.");
   	}

	//called in handleKeyPressEvent in this class.
	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopMyDoctor(KeyCode ke){
		this.myDoctor.setDX(0);
		this.myDoctor.setDY(0);
	}

}
