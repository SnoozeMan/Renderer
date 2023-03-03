package com.base.engine;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {

    private static long window;

    public static void createWindow(int width, int height, String title) {
        if(!glfwInit()) {
            System.out.println("GLFW Initialization error!");
            return;
        }
        window = glfwCreateWindow(width, height, title, 0, 0);
        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
        //glfwSwapInterval(1); // Enable v-sync TODO: MAYBE?
        glfwMakeContextCurrent(window); // Make the OpenGL context current
        glfwShowWindow(window);
        glfwSetKeyCallback(window, new Input());
    }

    public static void dispose() {
        glfwDestroyWindow(window);
    }

    public static void render() {
        glfwPollEvents();
    }

    public static void swapBuffers() {
        glfwSwapBuffers(window);
    }

    public static boolean isCloseRequested() {
        return glfwWindowShouldClose(window);
    }

    public static int getWidth() {
        int[] width = new int[1];
        glfwGetWindowSize(window, width, null);
        return width[0];
    }

    public static int getHeight() {
        int[] height = new int[1];
        glfwGetWindowSize(window, null, height);
        return height[0];
    }

    public static String getTitle() {
        return glfwGetVideoMode(glfwGetWindowMonitor(window)).toString();
    }
}
