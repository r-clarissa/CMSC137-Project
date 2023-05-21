package game;

import javafx.scene.image.Image;
import stages.SuperStage;

public class PowerUps extends Sprite {

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
			case ALCOHOL_TYPE: 	this.loadImage(SuperStage.alcohol); break;
			case FACEMASK_TYPE: this.loadImage(SuperStage.facemask); break;
			case VITAMINS_TYPE: this.loadImage(SuperStage.vitamins); break;
		}

	}

	void benefits(Player doctor, long time) {
		if (this.powerupType == this.FACEMASK_TYPE) facemaskPower(doctor);
		else if (this.powerupType == this.VITAMINS_TYPE) vitaminsPower(doctor);
		else if (this.powerupType == this.ALCOHOL_TYPE) alcoholPower(doctor, time);
	}

	private void vitaminsPower(Player doctor) {
		doctor.setVitaminsEffect(true);
	}

	private void facemaskPower(Player doctor) {
		doctor.addStrength(ADDITIONAL_HEALTH);
	}

	private void alcoholPower(Player doctor, long time) {
		if (time <= 30) doctor.addStrength(ADDITIONAL_HEALTH);
		else doctor.addStrength(100);
	}

	int getType() {
		return this.powerupType;
	}

}
