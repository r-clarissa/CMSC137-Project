package app;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import chat.ClientHandler;
// Import statements
import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import stages.TitleScreen;



public class Main extends Application {
	 private static final DatagramSocket socket;

	    static {
	        try {
	            socket = new DatagramSocket(); // init to any available port
	        } catch (SocketException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    private static final InetAddress address;

	    static {
	        try {
	            address = InetAddress.getByName("localhost");
	        } catch (UnknownHostException e) {
	            throw new RuntimeException(e);
	        }
	    }


	    private static final int SERVER_PORT = 8001; // send to server

	    private static final TextArea messageArea = new TextArea();

	    private static final TextField inputBox = new TextField();




	@Override
	public void start(Stage stage) throws Exception {

		TitleScreen titlescreen= new TitleScreen(socket, address,messageArea, inputBox);
		titlescreen.setStage(stage);


	}
	public static void main(String[] args) throws UnknownHostException, IOException {


		ClientHandler clientHandler = new ClientHandler(socket, messageArea);
		clientHandler.start();

	    // send initialization message to the server
        byte[] uuid = ("A client has connected").getBytes();
        DatagramPacket initialize = new DatagramPacket(uuid, uuid.length, address, SERVER_PORT);
        socket.send(initialize);
        launch();
	}

}
