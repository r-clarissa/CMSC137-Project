package stages;

import stages.SuperStage;

import java.net.DatagramSocket;
import java.net.InetAddress;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TitleScreen {

	private Group root;
	private Scene scene;


	private GraphicsContext gc;
	private Canvas canvas;
	private DatagramSocket socket;

    private InetAddress address;

    private TextArea messageArea;

    private TextField inputBox;
	public TitleScreen(DatagramSocket socket, InetAddress address,TextArea messageArea,TextField inputBox) {
		this.root = new Group();
		this.scene = new Scene(root, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);

		this.canvas = new Canvas(SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();

		this.socket=socket;
		this.address=address;
		this.messageArea=messageArea;
		this.inputBox=inputBox;

	}

	// Method for setting up the title stage
	public void setStage(Stage stage) {

		gc.clearRect(0, 0, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		gc.drawImage(SuperStage.landingPage, 0, 0);

		Button singlePlayerBtn = new Button("Single Player");
		SuperStage.designBtn(750, 154, singlePlayerBtn);

		singlePlayerBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {




				SelectTokenStage selectTokenStage = new SelectTokenStage(socket,address,messageArea,inputBox);
				selectTokenStage.setStage(stage);


			}
		});

		Button multiPlayerBtn = new Button("Multiplayer");
		SuperStage.designBtn(750, 257, multiPlayerBtn);

		multiPlayerBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				SelectTokenStage selectTokenStage = new SelectTokenStage(socket,address,messageArea,inputBox);
				selectTokenStage.setStage(stage);
			}
		});

		Button aboutBtn = new Button("About");
		SuperStage.designBtn(750, 360, aboutBtn);

		aboutBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				About aboutStage = new About(socket,address,messageArea,inputBox);
				aboutStage.setStage(stage);
			}
		});

		Button instructionBtn = new Button("Instructions");
		SuperStage.designBtn(750, 462, instructionBtn);

		instructionBtn.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				Instruction instructionStage = new Instruction(socket,address,messageArea,inputBox);
				instructionStage.setStage(stage);
			}
		});

		this.root.getChildren().addAll(canvas, singlePlayerBtn, multiPlayerBtn, aboutBtn, instructionBtn);

		stage.setTitle("The Pandemic Hero");
		stage.getIcons().add(SuperStage.icon);
		stage.setScene(this.scene);
		stage.show();

	}

}