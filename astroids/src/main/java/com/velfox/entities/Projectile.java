package com.velfox.entities;

import javafx.scene.shape.Polygon;

public class Projectile extends Character {

    private int lifetime; // Ticks before the projectile disappears

    public Projectile(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
        this.lifetime = 100; // Example lifetime in ticks (adjust as needed)
    }

    @Override
    public void move() {
        super.move();

        // Reduce lifetime
        this.lifetime--;

        // If lifetime expires, mark as not alive
        if (this.lifetime <= 0) {
            this.setAlive(false);
        }

        // Gradually reduce speed
        this.reduceSpeedOverTime();
    }

    private void reduceSpeedOverTime() {
        // Gradually reduce the speed vector to simulate deceleration
        this.setMovement(this.getMovement().multiply(0.98)); // Example deceleration factor
    }
}
