package stages;
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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class SelectTokenStage {

	private Group root;
	private Scene scene;
	private GraphicsContext gc;
	private Canvas canvas;

	private Button token1;
	private Button token2;
	private Button token3;
	private Button token4;
	private Button startBtn;

	private int tokenType;

	private DatagramSocket socket;

    private InetAddress address;

    private TextArea messageArea;

    private TextField inputBox;

    private TextField getUsername= new TextField();

    private String username="";

	public SelectTokenStage(DatagramSocket socket, InetAddress address,TextArea messageArea,TextField inputBox) {
		this.root = new Group();
		this.scene = new Scene(root, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.socket=socket;
		this.address=address;
		this.messageArea=messageArea;
		this.inputBox=inputBox;



		this.canvas = new Canvas(SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	}

	// Method for setting up the select token stage
	public void setStage(Stage stage) {

		gc.clearRect(0, 0, SuperStage.WINDOW_WIDTH, SuperStage.WINDOW_HEIGHT);
		gc.drawImage(SuperStage.selectTokenPage, 0, 0);

		this.getUsername.setMaxWidth(500);
        this.getUsername.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                this.username = this.getUsername.getText(); // message to send
                System.out.println(this.username);

            }
        });


		// Tokens
		token1 = SuperStage.designBtn(99, 240, 1);

		token2 = SuperStage.designBtn(362, 240, 2);

		token3 = SuperStage.designBtn(640, 240, 3);

		token4 = SuperStage.designBtn(912, 240, 4);

		// Event handlers for token buttons
		token1.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				token1Chosen();
			}
		});

		token2.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				token2Chosen();
			}
		});

		token3.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				token3Chosen();
			}
		});

		token4.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				token4Chosen();
			}
		});

		// Back btn
		Button backBtn = new Button("Back");
		SuperStage.designBtn(54, 548, backBtn);

		backBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				TitleScreen titlescreen = new TitleScreen(socket,address,messageArea,inputBox);
				titlescreen.setStage(stage);
			}
		});

		// Start game
		startBtn = new Button("Start Game");
		SuperStage.designBtn(820, 548, startBtn);
		startBtn.setDisable(true);

		startBtn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				TransitionStage countdown = new TransitionStage(tokenType,socket,address,messageArea,inputBox, username);
				countdown.setStage(stage, false);
			}
		});

		this.root.getChildren().addAll(canvas, token1, token2, token3, token4, backBtn, startBtn,getUsername);

		stage.setTitle("The Pandemic Hero");
		stage.getIcons().add(SuperStage.icon);
		stage.setScene(this.scene);
		stage.show();

	}

	// Method for disabling other tokens once clicked.
	private static void disableBtn(Button btn) {
		btn.setDisable(true);
		btn.setOpacity(0.25);
	}

	// Token 1 Chosen
	private void token1Chosen() {
		disableBtn(this.token2);
		disableBtn(this.token3);
		disableBtn(this.token4);
		this.startBtn.setDisable(false);
		tokenType = 1;
	}

	// Token 2 Chosen
	private void token2Chosen() {
		disableBtn(this.token1);
		disableBtn(this.token3);
		disableBtn(this.token4);
		this.startBtn.setDisable(false);
		tokenType = 2;
	}

	// Token 3 Chosen
	private void token3Chosen() {
		disableBtn(this.token1);
		disableBtn(this.token2);
		disableBtn(this.token4);
		this.startBtn.setDisable(false);
		tokenType = 3;
	}

	// Token 4 Chosen
	private void token4Chosen() {
		disableBtn(this.token1);
		disableBtn(this.token2);
		disableBtn(this.token3);
		this.startBtn.setDisable(false);
		tokenType = 4;
	}

}