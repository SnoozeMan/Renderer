package com.base.engine;

public class Vector2f {
    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    public float dot(Vector2f vector) {
        return vector.getX() * x + vector.getY() * y;
    }

    public Vector2f normalize() {
        float length = length();
        x /= length;
        y /= length;
        return this;
    }

    public Vector2f rotate(float angle) {
        double radians = Math.toRadians(angle);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        return new Vector2f((float)(x * cos - y * sin),(float)(x * sin + y * cos));
    }

    public Vector2f add(Vector2f vector) {
        return new Vector2f(vector.getX() + x, vector.getY() + y);
    }

    public Vector2f add(float value) {
        return new Vector2f(value + x, value + y);
    }

    public Vector2f subtract(Vector2f vector) {
        return new Vector2f(vector.getX() - x, vector.getY() - y);
    }

    public Vector2f subtract(float value) {
        return new Vector2f(value - x, value - y);
    }

    public Vector2f multiply(Vector2f vector) {
        return new Vector2f(vector.getX() * x, vector.getY() * y);
    }

    public Vector2f multiply(float value) {
        return new Vector2f(value * x, value * y);
    }
    public Vector2f divide(Vector2f vector) {
        return new Vector2f(vector.getX() / x, vector.getY() / y);
    }

    public Vector2f divide(float value) {
        return new Vector2f(value + x, value + y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}