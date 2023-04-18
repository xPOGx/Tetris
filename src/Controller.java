import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static Tetris game;
    private Timeline oneSecondWonder;
    private Timeline refreshRate;
    @FXML
    private Button fxButtonRestart;
    @FXML
    private VBox fxVBoxGO;
    @FXML
    private Label scoreLabelFx;
    @FXML
    private Label linesLabelFx;
    @FXML
    private GridPane fxGrid;
    private Label[][] storage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scoreLabelFx.setText("Score: 0");
        linesLabelFx.setText("Lines: 0");
        game = new Tetris(10, 20);
        storage = new Label[20][10];

        int[][] original = game.getField().getMatrix();
        //init grid
        for (int i = 0; i < fxGrid.getRowCount(); i++) {
            for (int j = 0; j < fxGrid.getColumnCount(); j++) {
                String body;
                if (original[i][j] == 0) {
                    body = "";
                } else {
                    body = "X";
                }
                Label text = new Label(body);
                storage[i][j] = text;
                fxGrid.add(text, j, i);
            }
        }

        oneSecondWonder = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            game.step();
            if (!game.isGameOver()) {
                game.getField().setScore(game.getField().getScore() + 1);
                scoreLabelFx.setText("Score: " + game.getField().getScore());
                linesLabelFx.setText("Lines: " + game.getField().getLines());
            } else {
                stopGame();
            }
        }));
        oneSecondWonder.setCycleCount(Timeline.INDEFINITE);

        refreshRate = new Timeline(new KeyFrame(Duration.seconds(0.017), event -> updateGrid(game.getField().print())));
        refreshRate.setCycleCount(Timeline.INDEFINITE);

        startGame();
    }

    private void updateGrid(int[][] grid) {
        for (int i = 0; i < storage.length; i++) {
            for (int j = 0; j < storage[i].length; j++) {
                String body;
                if (grid[i][j] == 0) {
                    body = "";
                } else {
                    body = "X";
                }
                storage[i][j].setText(body);
            }
        }
    }

    private void startGame() {
        refreshRate.play();
        oneSecondWonder.play();
    }
    private void stopGame() {
        refreshRate.pause();
        oneSecondWonder.pause();
        fxVBoxGO.setVisible(true);
        fxButtonRestart.setVisible(true);
    }

    public static Tetris getGame() {
        return game;
    }

    @FXML
    public void restart() {
        game = new Tetris(10, 20);
        updateGrid(game.getField().getMatrix());
        fxVBoxGO.setVisible(false);
        fxButtonRestart.setVisible(false);
        scoreLabelFx.setText("Score: 0");
        linesLabelFx.setText("Lines: 0");
        startGame();
    }
}