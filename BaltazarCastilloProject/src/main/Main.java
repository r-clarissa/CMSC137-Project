package main;

import javafx.application.Application;
import javafx.stage.Stage;
import gamepackage.GameStage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){

		// also add the timer to GameStage
		GameStage theGameStage = new GameStage();
		theGameStage.setStage(stage);

	}
}
