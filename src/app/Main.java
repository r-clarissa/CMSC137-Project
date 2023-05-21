package app;

// Import statements
import javafx.application.Application;
import javafx.stage.Stage;
import stages.TitleScreen;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		TitleScreen titleScreen = new TitleScreen();
		titleScreen.setStage(stage);

		/* test */
	}



}
