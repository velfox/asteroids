package com.velfox.core;

import com.velfox.entities.Asteroid;
import com.velfox.entities.Projectile;
import com.velfox.entities.Ship;
import com.velfox.input.InputHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GameLoop extends AnimationTimer {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    private final Ship ship;
    private final List<Asteroid> asteroids;
    private final List<Projectile> projectiles;
    private final InputHandler inputHandler;
    private final Pane pane;
    private final Text scoreText;
    private final AtomicInteger score = new AtomicInteger();

    public GameLoop(Ship ship, List<Asteroid> asteroids, List<Projectile> projectiles, InputHandler inputHandler, Pane pane, Text scoreText) {
        this.ship = ship;
        this.asteroids = asteroids;
        this.projectiles = projectiles;
        this.inputHandler = inputHandler;
        this.pane = pane;
        this.scoreText = scoreText;
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
    }

    private void fireProjectile() {
        if (projectiles.size() < 20) {
            Projectile projectile = new Projectile((int) ship.getCharacter().getTranslateX(), (int) ship.getCharacter().getTranslateY());
            projectile.getCharacter().setRotate(ship.getCharacter().getRotate());
            projectile.accelerate();
            projectiles.add(projectile);
            pane.getChildren().add(projectile.getCharacter());
        }
    }

    private void updateEntities() {
        ship.move();
        asteroids.forEach(Asteroid::move);
        projectiles.forEach(Projectile::move);
    }

    private void checkCollisions() {
        projectiles.forEach(projectile -> {
            asteroids.forEach(asteroid -> {
                if (projectile.collide(asteroid)) {
                    projectile.setAlive(false);
                    asteroid.setAlive(false);
                    scoreText.setText("Points: " + score.addAndGet(1000));
                }
            });
        });

        projectiles.removeIf(projectile -> !projectile.isAlive());
        asteroids.removeIf(asteroid -> !asteroid.isAlive());

        asteroids.forEach(asteroid -> {
            if (ship.collide(asteroid)) {
                stop();
                System.out.println("Game Over! Final Score: " + score.get());
            }
        });
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
