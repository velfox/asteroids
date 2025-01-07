package com.velfox.core;

import com.velfox.entities.Asteroid;
import com.velfox.entities.Projectile;
import com.velfox.entities.Ship;
import com.velfox.input.InputHandler;
import com.velfox.utilitys.Constants;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GameLoop extends AnimationTimer {

    private static final int WIDTH = Constants.SCREEN_WIDTH;
    private static final int HEIGHT = Constants.SCREEN_HEIGHT;

    private final Ship ship;
    private final List<Asteroid> asteroids;
    private final List<Projectile> projectiles;
    private final InputHandler inputHandler;
    private final Pane pane;
    private final Text scoreText;
    private final SceneController sceneController;
    private final AtomicInteger score = new AtomicInteger();

    public GameLoop(Ship ship, List<Asteroid> asteroids, List<Projectile> projectiles, 
                    InputHandler inputHandler, Pane pane, Text scoreText, SceneController sceneController) {
        this.ship = ship;
        this.asteroids = asteroids;
        this.projectiles = projectiles;
        this.inputHandler = inputHandler;
        this.pane = pane;
        this.scoreText = scoreText;
        this.sceneController = sceneController;
    }

    @Override
    public void handle(long now) {
        handleInput();
        updateEntities();
        checkCollisions();
        spawnAsteroids();
    }

    private void handleInput() {
        if (inputHandler.isKeyPressed(KeyCode.LEFT)) ship.turnLeft();
        if (inputHandler.isKeyPressed(KeyCode.RIGHT)) ship.turnRight();
        if (inputHandler.isKeyPressed(KeyCode.UP)) ship.accelerate();
        if (inputHandler.isKeyPressed(KeyCode.SPACE)) fireProjectile();

        // Pauzeren van de game en terugkeren naar het menu via ESC
        if (inputHandler.isKeyPressed(KeyCode.ESCAPE)) {
            stop(); // Stop de gameloop
            sceneController.showMenuScene(); // Ga naar het menu
        }
    }

    private void fireProjectile() {
        if (projectiles.size() < 20) {
            Projectile projectile = new Projectile(
                (int) ship.getCharacter().getTranslateX(),
                (int) ship.getCharacter().getTranslateY()
            );
            projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
    
            // Zet de snelheid 20 keer sneller
            double speedMultiplier = 20.0;
            double changeX = Math.cos(Math.toRadians(ship.getCharacter().getRotate())) * speedMultiplier;
            double changeY = Math.sin(Math.toRadians(ship.getCharacter().getRotate())) * speedMultiplier;
    
            projectile.setMovement(projectile.getMovement().add(changeX, changeY));
            projectiles.add(projectile);
            pane.getChildren().add(projectile.getCharacter());
        }
    }

    private void updateEntities() {
        ship.move();
        asteroids.forEach(Asteroid::move);
        projectiles.forEach(Projectile::move);

            // Verwijder projectielen die niet meer "alive" zijn
        projectiles.removeIf(projectile -> {
            if (!projectile.isAlive()) {
                pane.getChildren().remove(projectile.getCharacter());
                return true;
            }
            return false;
        });
    }

    private void checkCollisions() {
        projectiles.forEach(projectile -> {
            asteroids.forEach(asteroid -> {
                if (projectile.collide(asteroid)) {
                    projectile.setAlive(false);
                    createImplosionEffect(asteroid); // Start implosie-effect

                     // Verwijder het projectile uit het paneel
                    pane.getChildren().remove(projectile.getCharacter());
                    
                    asteroid.setAlive(false);
                    scoreText.setText("Points: " + score.addAndGet(1000));
                }
            });
        });

        projectiles.removeIf(projectile -> !projectile.isAlive());
        asteroids.removeIf(asteroid -> !asteroid.isAlive());

        asteroids.forEach(asteroid -> {
            if (ship.collide(asteroid)) {
                stop(); // Stop de gameloop
                sceneController.showGameOverScene(score.get()); // Toon Game Over-scherm
            }
        });
    }

    private void createImplosionEffect(Asteroid asteroid) {
        javafx.animation.ScaleTransition scaleTransition = new javafx.animation.ScaleTransition();
        scaleTransition.setNode(asteroid.getCharacter());
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.0); // Implodeer naar niets
        scaleTransition.setToY(0.0);
        scaleTransition.setDuration(javafx.util.Duration.millis(300)); // Duur van de implosie
    
        scaleTransition.setOnFinished(e -> {
            pane.getChildren().remove(asteroid.getCharacter()); // Verwijder de asteroid uit het paneel
        });
    
        scaleTransition.play();
    }
    

    private void spawnAsteroids() {
        if (Math.random() < 0.005) {
            Random random = new Random();
            Asteroid asteroid = new Asteroid(random.nextInt(WIDTH), random.nextInt(HEIGHT));
            if (!asteroid.collide(ship)) {
                asteroids.add(asteroid);
                pane.getChildren().add(asteroid.getCharacter());
            }
        }
    }
}
