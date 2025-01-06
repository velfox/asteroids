package com.velfox.UI;

import com.velfox.core.SceneController;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameUI {

    private SceneController sceneController;
    private Pane gameLayout;

    public GameUI(SceneController sceneController) {
        this.sceneController = sceneController;
        this.gameLayout = new Pane();
        setupGame();
    }

    public Scene getScene() {
        return new Scene(gameLayout, 600, 400);
    }

    private void setupGame() {
        Text placeholder = new Text(250, 200, "Game Scene (Implement Gameplay Here)");
        gameLayout.getChildren().add(placeholder);

        // Add back button for debugging
        // Remove in production if you don't want users to go back during gameplay
        Text backToMenu = new Text(10, 380, "Press ESC to return to Menu");
        gameLayout.getChildren().add(backToMenu);

        gameLayout.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ESCAPE:
                    sceneController.showMenuScene();
                    break;
                default:
                    break;
            }
        });
        gameLayout.requestFocus();
    }
}
