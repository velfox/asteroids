package com.velfox.core;

import com.velfox.entities.Asteroid;
import com.velfox.entities.Projectile;
import com.velfox.entities.Ship;
import com.velfox.input.InputHandler;
import com.velfox.utilities.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameInitializer {

    private Pane pane;

    public GameInitializer(Pane pane) {
        this.pane = pane;
    }

    public void initialize() {
        pane.getChildren().clear();

        Text scoreText = new Text(10, 20, "Points: 0");
        pane.getChildren().add(scoreText);

        InputHandler inputHandler = new InputHandler();

        Ship ship = new Ship(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
        pane.getChildren().add(ship.getCharacter());

        List<Projectile> projectiles = new ArrayList<>();
        List<Asteroid> asteroids = createInitialAsteroids(5);
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter()));

        GameLoop gameLoop = new GameLoop(ship, asteroids, projectiles, inputHandler, pane, scoreText);
        gameLoop.start();
    }

    private List<Asteroid> createInitialAsteroids(int count) {
        Random random = new Random();
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            asteroids.add(new Asteroid(random.nextInt(Constants.SCREEN_WIDTH), random.nextInt(Constants.SCREEN_HEIGHT)));
        }
        return asteroids;
    }
}
