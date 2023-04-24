package com.base.engine;

public class Game {
    private Mesh mesh;
    private Shader shader;
    private Transform transform;
    private Camera camera;

    public Game() {
        shader = new Shader();
        camera = new Camera();
        mesh = ResourceLoader.loadMesh("cube.obj");
        Transform.setCamera(camera);
        transform = new Transform();
        Transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        shader.compileShader();
        shader.addUniform("transform");
    }

    public void input() {
        camera.input();
    }

    /** Inactive code for manipulating the cube with sinus function **/
    float temp = 0;
    public void update() {
        /*temp += Time.getDelta();
        float sinTemp = (float)Math.sin(temp);
        transform.setTranslation(0.2f * sinTemp, 0.2f * sinTemp, 0.2f * sinTemp + 3 + 1);
        transform.setRotation(temp * 80, temp * 80, temp * 80);
        transform.setScale(0.2f * sinTemp + 0.8f, 0.2f * sinTemp + 0.8f, 0.2f * sinTemp + 0.8f);*/
    }

    public void render() {
        shader.setUniform("transform", transform.getProjectedTransformation());
        shader.bind();
        mesh.draw();
    }
}
