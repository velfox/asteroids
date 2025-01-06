package com.velfox.input;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {

    private final Map<KeyCode, Boolean> keyStates = new HashMap<>();

    public void onKeyPressed(KeyEvent event) {
        keyStates.put(event.getCode(), true);
    }

    public void onKeyReleased(KeyEvent event) {
        keyStates.put(event.getCode(), false);
    }

    public boolean isKeyPressed(KeyCode key) {
        return keyStates.getOrDefault(key, false);
    }
}
