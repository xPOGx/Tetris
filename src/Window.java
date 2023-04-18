import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class Window extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        Scene scene = new Scene(root);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler());

        stage.setTitle("Tetris");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public EventHandler<KeyEvent> keyboardHandler() {
        return keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT -> Controller.getGame().getFigure().left();
                case RIGHT -> Controller.getGame().getFigure().right();
                case SPACE -> Controller.getGame().getFigure().downMaximum();
                case UP -> Controller.getGame().getFigure().rotate();
            }
        };
    }
}
