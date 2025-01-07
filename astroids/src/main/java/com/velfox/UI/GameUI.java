package com.velfox.UI;

import com.velfox.core.SceneController;
import com.velfox.entities.Asteroid;
import com.velfox.entities.Projectile;
import com.velfox.entities.Ship;
import com.velfox.input.InputHandler;
import com.velfox.core.GameLoop;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GameUI {

    private final SceneController sceneController;
    private final InputHandler inputHandler;
    private final Ship ship;
    private final List<Asteroid> asteroids;
    private final List<Projectile> projectiles;

    public GameUI(SceneController sceneController) {
        this.sceneController = sceneController;
        this.inputHandler = new InputHandler();
        this.ship = new Ship(300, 300); // Voorbeeld beginpositie
        this.asteroids = new ArrayList<>();
        this.projectiles = new ArrayList<>();
    }

    public Scene getScene() {
        // Maak een nieuw Pane voor elke nieuwe Scene
        Pane gameLayout = new Pane();
        gameLayout.setPrefSize(600, 400);
        gameLayout.setStyle("-fx-background-color: black;");

        // Voeg UI-elementen toe
        Text scoreText = new Text(10, 20, "Points: 0");
        gameLayout.getChildren().add(scoreText);
        gameLayout.getChildren().add(ship.getCharacter());

        // Voeg asteroïden toe
        for (int i = 0; i < 5; i++) {
            Asteroid asteroid = new Asteroid((int) (Math.random() * 600), (int) (Math.random() * 400));
            asteroids.add(asteroid);
            gameLayout.getChildren().add(asteroid.getCharacter());
        }

        // Stel inputhandler in
        Scene scene = new Scene(gameLayout);
        scene.setOnKeyPressed(inputHandler::onKeyPressed);
        scene.setOnKeyReleased(inputHandler::onKeyReleased);

        // Start de GameLoop
        startGameLoop(gameLayout, scoreText);

        scene.setFill(Color.BLACK);
        scoreText.setFill(Color.WHITE); // Zorg dat de tekst zichtbaar blijft
    
        return scene;
    }

    private void startGameLoop(Pane gameLayout, Text scoreText) {
        GameLoop gameLoop = new GameLoop(
            ship, asteroids, projectiles, inputHandler, gameLayout, scoreText, sceneController
        );
        gameLoop.start();
    }
}
