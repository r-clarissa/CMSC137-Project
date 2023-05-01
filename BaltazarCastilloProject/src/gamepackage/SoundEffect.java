package gamepackage;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundEffect {

	private Clip clip;

	public void setFileBGM (String soundfileName){

		try{

			File file = new File(soundfileName);
			AudioInputStream soundfx = AudioSystem.getAudioInputStream(file);
			this.clip=AudioSystem.getClip();
			this.clip.open(soundfx);

		} catch (Exception e) {}

	}

	public void play(){

		this.clip.setFramePosition(0);
		this.clip.start();

	}



}
