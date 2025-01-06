package com.velfox.core;

import com.velfox.UI.MenuUI;
import com.velfox.UI.GameUI;
import com.velfox.UI.HighScoreUI;
import javafx.stage.Stage;

public class SceneController {

    private Stage stage;

    public SceneController(Stage stage) {
        this.stage = stage;
    }

    public void showMenuScene() {
        MenuUI menu = new MenuUI(this);
        stage.setScene(menu.getScene());
        stage.setTitle("Asteroids - Main Menu");
        stage.show();
    }

    public void showGameScene() {
        GameUI gameUI = new GameUI(this);
        stage.setScene(gameUI.getScene());
        stage.setTitle("Asteroids - Game");
        stage.show();
    }

    public void showHighScoreScene() {
        HighScoreUI highScoreUI = new HighScoreUI(this);
        stage.setScene(highScoreUI.getScene());
        stage.setTitle("Asteroids - High Scores");
        stage.show();
    }
}
