package com.velfox.UI;

import com.velfox.core.GameInitializer;
import com.velfox.core.SceneController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameUI {

    private SceneController sceneController;
    private Pane gameLayout;
    private Scene gameScene;

    public GameUI(SceneController sceneController) {
        this.sceneController = sceneController;
        this.gameLayout = new Pane();
        this.gameScene = new Scene(gameLayout, 500, 500); // Gebruik consistente grootte
        setupGame();
    }

    public Scene getScene() {
        return gameScene;
    }

    private void setupGame() {
        // Initialiseer de game via GameInitializer
        GameInitializer gameInitializer = new GameInitializer(gameLayout, sceneController);
        gameInitializer.initialize();

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                sceneController.showMenuScene();
            }
        });

        gameLayout.requestFocus();
    }
}
