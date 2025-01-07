package com.velfox.core;

import com.velfox.entities.Asteroid;
import com.velfox.entities.Projectile;
import com.velfox.entities.Ship;
import com.velfox.input.InputHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.velfox.utilitys.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameInitializer {

    private static final int WIDTH = Constants.SCREEN_HEIGHT;
    private static final int HEIGHT = Constants.SCREEN_WIDTH;

    private Stage stage;

    public GameInitializer(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        //opbouwen
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

        //start gameloop hier
        GameLoop gameLoop = new GameLoop(ship, asteroids, projectiles, inputHandler, pane, scoreText);
        gameLoop.start();

        // switch naar opgeboude scene
        stage.setScene(scene);
        stage.show();

        // Maak de SceneController en start met het menu
        SceneController sceneController = new SceneController(stage);
        sceneController.showMenuScene();
      
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
