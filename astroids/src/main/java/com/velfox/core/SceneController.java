package com.velfox.core;

import com.velfox.UI.MenuUI;
import com.velfox.UI.GameUI;
import com.velfox.UI.HighScoreUI;
import com.velfox.UI.GameOverUI;
import javafx.stage.Stage;

public class SceneController {

    private final Stage stage;
    private boolean gameRunning;

    public SceneController(Stage stage) {
        this.stage = stage;
        this.gameRunning = false;
    }

    public void showMenuScene() {
        MenuUI menu = new MenuUI(this);
        stage.setScene(menu.getScene());
        stage.setTitle("Asteroids - Main Menu");
        stage.show();
    }

    public void showGameScene() {
        gameRunning = true;
        GameUI game = new GameUI(this);
        stage.setScene(game.getScene());
        stage.setTitle("Asteroids - Game");
        stage.show();
    }

    public void showHighScoreScene() {
        HighScoreUI highScore = new HighScoreUI(this);
        stage.setScene(highScore.getScene());
        stage.setTitle("Asteroids - High Scores");
        stage.show();
    }

    public void showGameOverScene(int score) {
        gameRunning = false;
        GameOverUI gameOver = new GameOverUI(this, score);
        stage.setScene(gameOver.getScene());
        stage.setTitle("Asteroids - Game Over");
        stage.show();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }
}
