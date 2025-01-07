package com.velfox.UI;

import com.velfox.core.SceneController;
import com.velfox.utilitys.HighScoreManager;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GameOverUI {

    private final SceneController sceneController;
    private final int finalScore;

    public GameOverUI(SceneController sceneController, int finalScore) {
        this.sceneController = sceneController;
        this.finalScore = finalScore;
    }

    public Scene getScene() {
        if (HighScoreManager.isTopScore(finalScore)) {
            return getTopScoreScene();
        } else {
            return getNoHighScoreScene();
        }
    }

    /**
     * Scene voor als de speler een top 5 highscore behaalt.
     */
    private Scene getTopScoreScene() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("Congratulations! You made it to the Top 5!");
        Text scoreText = new Text("Your Score: " + finalScore);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                HighScoreManager.addHighScore(name, finalScore);
                sceneController.showMenuScene();
            }
        });

        layout.getChildren().addAll(title, scoreText, nameField, submitButton);
        return new Scene(layout, 600, 400);
    }

    /**
     * Scene voor als de speler geen highscore behaalt.
     */
    private Scene getNoHighScoreScene() {
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Text title = new Text("Game Over!");
        Text message = new Text("Unfortunately, you didn't make it to the Top 5.");
        Text scoreText = new Text("Your Score: " + finalScore);

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> sceneController.showMenuScene());

        layout.getChildren().addAll(title, message, scoreText, backButton);
        return new Scene(layout, 600, 400);
    }
}
