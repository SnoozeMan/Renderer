package com.base.engine;

import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderUtility {
    public static void clearScreen() {
        // TODO: STENCIL BUFFER
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    public static void initializeGraphics() {
        GL.createCapabilities();
        glClearColor(0, 0, 0,0);
        glFrontFace(GL_CW); // Clockwise is front face
        glCullFace(GL_BACK); // Cull back face
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST); // Z-depth testing
        // TODO: Depth clamp
        glEnable(GL_FRAMEBUFFER_SRGB); // Exponential correction for gamma correction
    }
}
