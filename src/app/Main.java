package app;

import javafx.application.Application;
import javafx.stage.Stage;
import stages.TitleScreen;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
//		GameStage theGameStage = new GameStage();
//		theGameStage.setStage(stage);

		TitleScreen theGameTitle = new TitleScreen();
		theGameTitle.setStage(stage);

		// update
	}

}
