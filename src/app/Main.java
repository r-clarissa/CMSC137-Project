package app;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

// Import statements
import javafx.application.Application;
import javafx.stage.Stage;
import stages.TitleScreen;

public class Main extends Application {



	@Override
	public void start(Stage stage) throws Exception {


		TitleScreen titleScreen = new TitleScreen();
		titleScreen.setStage(stage);
		@SuppressWarnings("resource")
		Scanner scanner= new Scanner(System.in);
		System.out.println("Enter your username for the group chat: ");
		String username= scanner.nextLine();
		Socket socket= new Socket("localhost",9992);
		Client client = new Client(socket, username);
		client.listenForMessage();
		client.sendMessage();


	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		launch(args);

	}

}
