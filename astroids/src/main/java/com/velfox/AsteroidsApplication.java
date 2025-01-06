package com.velfox;

import com.velfox.core.GameInitializer;
import javafx.application.Application;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Asteroids!");
        GameInitializer gameInitializer = new GameInitializer(stage);
        gameInitializer.initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
