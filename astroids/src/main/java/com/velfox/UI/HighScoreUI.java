package com.velfox.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import com.velfox.core.SceneController;

import javafx.geometry.Pos;
import javafx.scene.text.Text;

public class HighScoreUI {

    private SceneController sceneController;

    public HighScoreUI(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public Scene getScene() {
        VBox highScoreLayout = new VBox(15);
        highScoreLayout.setAlignment(Pos.CENTER);

        Text title = new Text("High Scores");
        Text placeholder = new Text("1. Player1 - 1000\n2. Player2 - 800\n3. Player3 - 500");

        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> sceneController.showMenuScene());

        highScoreLayout.getChildren().addAll(title, placeholder, backButton);

        return new Scene(highScoreLayout, 600, 400);
    }
}
