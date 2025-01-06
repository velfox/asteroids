package com.velfox.core;

import com.velfox.entities.Asteroid;
import com.velfox.entities.Projectile;
import com.velfox.entities.Ship;
import com.velfox.input.InputHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameInitializer {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private Stage stage;

    public GameInitializer(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);

        Text scoreText = new Text(10, 20, "Points: 0");
        pane.getChildren().add(scoreText);

        InputHandler inputHandler = new InputHandler();
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(inputHandler::onKeyPressed);
        scene.setOnKeyReleased(inputHandler::onKeyReleased);

        Ship ship = new Ship(WIDTH / 2, HEIGHT / 2);
        pane.getChildren().add(ship.getCharacter());

        List<Projectile> projectiles = new ArrayList<>();
        List<Asteroid> asteroids = createInitialAsteroids(5);
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));

        GameLoop gameLoop = new GameLoop(ship, asteroids, projectiles, inputHandler, pane, scoreText);
        gameLoop.start();

        stage.setScene(scene);
        stage.show();
    }

    private List<Asteroid> createInitialAsteroids(int count) {
        Random random = new Random();
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            asteroids.add(new Asteroid(random.nextInt(WIDTH / 3), random.nextInt(HEIGHT)));
        }
        return asteroids;
    }
}
