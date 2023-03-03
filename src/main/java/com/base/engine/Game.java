package com.base.engine;

import org.lwjgl.glfw.GLFW;

public class Game {
    private Mesh mesh;

    public Game() {
        mesh = new Mesh();
        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1,-1,0)), new Vertex(new Vector3f(1,-1,0)), new Vertex(new Vector3f(0,1,0))};
        mesh.addVertices(data);
    }

    public void input() {
        if (Input.getPressedKey(GLFW.GLFW_KEY_SPACE)) {
            System.out.println("SPACE down");
        }
        if (Input.getLiftedKey(GLFW.GLFW_KEY_SPACE)) {
            System.out.println("SPACE up");
        }
    }
    public void update() {

    }
    public void render() {
        mesh.draw();
    }
}
