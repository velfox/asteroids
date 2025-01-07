package com.velfox.entities;

import java.util.Random;

import com.velfox.utilitys.PolygonFactory;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Asteroid extends Character {

    private double rotationalMovement;
    private final Random random = new Random();



    public Asteroid(int x, int y) {
        super(new PolygonFactory().createPolygon(), x, y);

        Random rnd = new Random();

        super.getCharacter().setRotate(rnd.nextInt(360));

        int accelerationAmount = 1 + rnd.nextInt(10);
        for (int i = 0; i < accelerationAmount; i++) {
            accelerate();
        }

        this.rotationalMovement = 0.5 - rnd.nextDouble();

            // Genereer een willekeurige tint bruin
            Color randomBrown = generateRandomBrown();
            this.getCharacter().setFill(randomBrown);
    }

    public void move() {
        super.move();
        super.getCharacter().setRotate(super.getCharacter().getRotate() + rotationalMovement);
    }

    private Color generateRandomBrown() {
        double red = 0.5 + (random.nextDouble() * 0.4); // Roodtint tussen 0.5 en 0.9
        double green = 0.3 + (random.nextDouble() * 0.2); // Groentint tussen 0.3 en 0.5
        double blue = 0.1 + (random.nextDouble() * 0.1); // Blauwtint tussen 0.1 en 0.2
        return new Color(red, green, blue, 1.0);
    }
}