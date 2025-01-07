package com.velfox.UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import com.velfox.core.SceneController;

import javafx.geometry.Pos;

public class MenuUI {

    private final SceneController sceneController;

    public MenuUI(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    public Scene getScene() {
        VBox menuLayout = new VBox(15);
        menuLayout.setAlignment(Pos.CENTER);

        Button startButton = new Button(sceneController.isGameRunning() ? "Restart Game" : "Start Game");
        startButton.setOnAction(e -> sceneController.showGameScene());

        Button highScoresButton = new Button("High Scores");
        highScoresButton.setOnAction(e -> sceneController.showHighScoreScene());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> System.exit(0));

        menuLayout.getChildren().addAll(startButton, highScoresButton, exitButton);

        return new Scene(menuLayout, 600, 400);
    }
}
