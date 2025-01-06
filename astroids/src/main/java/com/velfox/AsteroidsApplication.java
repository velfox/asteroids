package com.velfox;

import com.velfox.core.SceneController;
import com.velfox.utilitys.Constants;
import javafx.application.Application;
import javafx.stage.Stage;

public class AsteroidsApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Asteroids!");

        // Controleer of het menu moet worden overgeslagen
        SceneController sceneController = new SceneController(stage);
        if (Constants.SKIP_MENU) {
            sceneController.showGameScene(); // Direct naar het spel
        } else {
            sceneController.showMenuScene(); // Toon het menu
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
