package com.velfox.core;

import javafx.stage.Stage;

public class GameInitializer {

    private final Stage stage;

    public GameInitializer(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        // Maak de SceneController en start met het menu
        SceneController sceneController = new SceneController(stage);
        sceneController.showMenuScene();
    }
}
