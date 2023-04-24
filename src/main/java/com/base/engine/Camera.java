package com.base.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;

public class Camera {
    public static final Vector3f yAxis = new Vector3f(0, 1, 0);
    private Vector3f position;
    private Vector3f forward;
    private Vector3f up;

    public Camera() {
        this(new Vector3f(0, 0, 3), new Vector3f(0, 0, -1), new Vector3f(0, 1, 0));
    }

    public Camera(Vector3f position, Vector3f forward, Vector3f up) {
        this.position = position;
        this.forward = forward;
        this.up = up;
        this.forward.normalize();
        this.up.normalize();
    }

    public void input() {
        float moveAmount = (float)(10 * Time.getDelta());
        float rotationAmount = (float)(100 * Time.getDelta());
        if (Input.getKey(GLFW_KEY_W)) {
            move(getForward(), moveAmount);
        }
        if (Input.getKey(GLFW_KEY_S)) {
            move(getForward(), -moveAmount);
        }
        if (Input.getKey(GLFW_KEY_A)) {
            move(getLeft(), moveAmount);
        }
        if (Input.getKey(GLFW_KEY_D)) {
            move(getRight(), moveAmount);
        }
        if (Input.getKey(GLFW_KEY_UP)) {
            rotateX(-rotationAmount);
        }
        if (Input.getKey(GLFW_KEY_DOWN)) {
            rotateX(rotationAmount);
        }
        if (Input.getKey(GLFW_KEY_LEFT)) {
            rotateY(-rotationAmount);
        }
        if (Input.getKey(GLFW_KEY_RIGHT)) {
            rotateY(rotationAmount);
        }
    }

    public void move(Vector3f direction, float amount) {
        position = position.add(direction.multiply(amount));
    }

    public Vector3f getLeft() {
        Vector3f left = forward.cross(up);
        left.normalize(); // Just in case
        return left;
    }

    public Vector3f getRight() {
        Vector3f right = up.cross(forward);
        right.normalize(); // Just in case
        return right;
    }

    public void rotateX(float angle) {
        Vector3f hAxis = yAxis.cross(forward);
        hAxis.normalize();
        forward.rotate(angle, hAxis);
        forward.normalize();
        up = forward.cross(hAxis);
        up.normalize();
    }

    public void rotateY(float angle) {
        Vector3f hAxis = yAxis.cross(forward);
        hAxis.normalize();
        forward.rotate(angle, yAxis);
        forward.normalize();
        up = forward.cross(hAxis);
        up.normalize();
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }
}
