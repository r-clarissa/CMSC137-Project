/*
This is a mini shooting game that involves a shooter and zombies. Throughout the game, power
ups spawns in random locations. This program also integrates the OOP concepts learned throughout
the semester.

@ authors:
	Rodriguez, Clarissa G.
	Sotto, Kyle S.

created date:
2021-12-1  18:50
*/
package main;

import application.SplashScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		SplashScreen splashScreen = new SplashScreen();
		splashScreen.setStage(stage);
	}

}
