package game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Chat extends Application {
    private TextArea chatArea;
    private TextField inputField;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setPadding(new Insets(0)); // Remove the top padding

        VBox chatBox = new VBox();
        ScrollPane scrollPane = new ScrollPane(chatBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setCenter(scrollPane);

        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        HBox inputFieldContainer = new HBox();
        inputFieldContainer.setAlignment(Pos.CENTER_RIGHT);
        inputFieldContainer.setSpacing(5);

        inputField = new TextField();
        inputField.setOnAction(e -> sendMessage());
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());
        inputFieldContainer.getChildren().addAll(inputField, sendButton);

        VBox inputBox = new VBox(10, chatArea, inputFieldContainer);
        inputBox.setPadding(new Insets(10));
        inputBox.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setBottom(inputBox);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Chat Box");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage() {
        String message = inputField.getText();
        if (!message.isEmpty()) {
            chatArea.appendText("You: " + message + "\n");
            inputField.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
