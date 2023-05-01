package game;

import javafx.scene.image.Image;

public class PowerUps extends Sprite {

	private final static Image ALCOHOL_IMAGE = new Image("images/Alcohol.png",50,90,false,false);
	private final static Image FACEMASK_IMAGE = new Image("images/Facemask.png",50,25,false,false);
	private final static Image VITAMINS_IMAGE = new Image("images/Vitamins.png",50,60,false,false);

	public final static int ALCOHOL_TYPE = 1;
	public final static int FACEMASK_TYPE = 2;
	public final static int VITAMINS_TYPE = 3;

	private final static int TOTAL_TYPES = 3;
	private int powerupType;

	public static final int ADDITIONAL_HEALTH = 50;
	private boolean get;
	private int addHealth;


	PowerUps(int x, int y, int powerupType) {
		// TODO Auto-generated constructor stub
		super(x,y);
		this.powerupType = powerupType;
		// TODO Auto-generated constructor stub
		this.get = false;

		//if (this.powerupType == 1) this.loadImage(PowerUps.POWERUPS_IMAGE);
		switch (powerupType) {
			case ALCOHOL_TYPE: 	this.loadImage(ALCOHOL_IMAGE); break;
			case FACEMASK_TYPE: this.loadImage(FACEMASK_IMAGE); break;
			case VITAMINS_TYPE: this.loadImage(VITAMINS_IMAGE); break;
		}

	}

	void benefits(Doctor doctor, long time) {
		if (this.powerupType == this.FACEMASK_TYPE) facemaskPower(doctor);
		else if (this.powerupType == this.VITAMINS_TYPE) vitaminsPower(doctor);
		else if (this.powerupType == this.ALCOHOL_TYPE) alcoholPower(doctor, time);
	}

	private void vitaminsPower(Doctor doctor) {
		doctor.setVitaminsEffect(true);
	}

	private void facemaskPower(Doctor doctor) {
		doctor.addStrength(ADDITIONAL_HEALTH);
	}

	private void alcoholPower(Doctor doctor, long time) {
		if (time <= 30) doctor.addStrength(ADDITIONAL_HEALTH);
		else doctor.addStrength(100);
	}

	int getType() {
		return this.powerupType;
	}

}
