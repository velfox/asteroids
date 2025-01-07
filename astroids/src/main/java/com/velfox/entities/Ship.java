package com.velfox.entities;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Ship extends Character {

    public Ship(int x, int y) {
        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);

        // Stel de vulkleur van het schip in op blauw
        this.getCharacter().setFill(Color.BLUE);
    }
}