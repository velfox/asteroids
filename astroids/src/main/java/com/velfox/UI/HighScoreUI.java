package com.velfox.UI;

import com.velfox.core.SceneController;
import com.velfox.utilitys.HighScoreManager;
import com.velfox.utilitys.HighScoreManager.HighScoreEntry;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class HighScoreUI {

    private final SceneController sceneController;

    public HighScoreUI(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public Scene getScene() {
        VBox highScoreLayout = new VBox(15);
        highScoreLayout.setAlignment(Pos.CENTER);

        Text title = new Text("High Scores");

        List<HighScoreEntry> highScores = HighScoreManager.getHighScores();
        VBox scoresList = new VBox(10);
        scoresList.setAlignment(Pos.CENTER);

        if (highScores.isEmpty()) {
            scoresList.getChildren().add(new Text("No high scores yet. Be the first to play!"));
        } else {
            for (int i = 0; i < highScores.size(); i++) {
                HighScoreEntry entry = highScores.get(i);
                scoresList.getChildren().add(new Text((i + 1) + ". " + entry.getName() + " - " + entry.getScore()));
            }
        }

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> sceneController.showMenuScene());

        highScoreLayout.getChildren().addAll(title, scoresList, backButton);
        return new Scene(highScoreLayout, 600, 400);
    }
}
