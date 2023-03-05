package com.base.engine;

import org.lwjgl.glfw.GLFW;

public class Game {
    private Mesh mesh;
    private Shader shader;
    private Transform transform;

    public Game() {
        shader = new Shader();
        /*
        mesh = new Mesh();
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0)),
                                          new Vertex(new Vector3f(0,1,0)),
                                          new Vertex(new Vector3f(1,-1,0)),
                                          new Vertex(new Vector3f(0,-1,1))};
        int[] indices = new int[] {0, 1, 3,
                                   3, 1, 2,
                                   2, 1, 0,
                                   0, 2, 3};
         */
        mesh = ResourceLoader.loadMesh("pyramid.obj");
        transform = new Transform();
        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        shader.compileShader();
        shader.addUniform("transform");
    }

    public void input() {
        if (Input.getPressedKey(GLFW.GLFW_KEY_SPACE)) {
            System.out.println("SPACE down");
        }
        if (Input.getLiftedKey(GLFW.GLFW_KEY_SPACE)) {
            System.out.println("SPACE up");
        }
    }

    float temp = 0;
    public void update() {
        temp += Time.getDelta();
        float sinTemp = (float)Math.sin(temp);
        transform.setTranslation(sinTemp, 0, 0);
        transform.setRotation(sinTemp * 180, sinTemp * 180, sinTemp * 180);
        transform.setScale(sinTemp, sinTemp, sinTemp);
    }

    public void render() {
        shader.setUniform("transform", transform.getTransformation());
        shader.bind();
        mesh.draw();
    }
}
