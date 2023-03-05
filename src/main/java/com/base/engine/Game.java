package com.base.engine;

import org.lwjgl.glfw.GLFW;

public class Game {
    private Mesh mesh;
    private Shader shader;

    public Game() {
        mesh = new Mesh();
        shader = new Shader();
        Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1,-1,0)), new Vertex(new Vector3f(0,1,0)), new Vertex(new Vector3f(1,-1,0))};
        mesh.addVertices(data);
        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        shader.compileShader();
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
        //DO UPDATE
    }
    public void render() {
        shader.bind();
        mesh.draw();
    }
}
