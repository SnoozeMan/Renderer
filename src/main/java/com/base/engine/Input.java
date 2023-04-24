package com.base.engine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {
    public static final int NUM_KEYCODES = 512;
    public static boolean[] keys = new boolean[NUM_KEYCODES];
    public static boolean[] currentKeys = new boolean[NUM_KEYCODES];
    public static boolean[] pressedKeys = new boolean[NUM_KEYCODES];
    public static boolean[] liftedKeys = new boolean[NUM_KEYCODES];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW.GLFW_RELEASE;
    }

    public static boolean getKey(int keyCode) {
        return keys[keyCode];
    }

    public static boolean getPressedKey(int keyCode) {
        return pressedKeys[keyCode];
    }

    public static boolean getLiftedKey(int keyCode) {
        return liftedKeys[keyCode];
    }

    /** Update keypress states */
    public static void update() {
        for (int i = 0; i < NUM_KEYCODES; i++) {
            if (!currentKeys[i] && keys[i]) {
                pressedKeys[i] = true;
            }
            else {
                pressedKeys[i] = false;
            }
            if (currentKeys[i] && !keys[i]) {
                liftedKeys[i] = true;
            } else {
                liftedKeys[i] = false;
            }
            currentKeys[i] = keys[i];
        }
    }
}
