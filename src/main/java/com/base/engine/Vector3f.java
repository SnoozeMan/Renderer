package com.base.engine;

public class Vector3f {
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3f vector) {
        return vector.getX() * x + vector.getY() * y + vector.getZ() * z;
    }

    public Vector3f cross(Vector3f vector) {
        float x_ = vector.getY() * z - vector.getZ() * y;
        float y_ = vector.getX() * z - vector.getZ() * x;
        float z_ = vector.getX() * y - vector.getY() * x;
        return new Vector3f(x_, y_, z_);
    }

    public Vector3f normalize() {
        float length = length();
        x /= length;
        y /= length;
        z /= length;
        return this;
    }

    public Vector2f rotate(float angle) {
        double radians = Math.toRadians(angle);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        return new Vector2f((float)(x * cos - y * sin),(float)(x * sin + y * cos));
    }

    public Vector3f add(Vector3f vector) {
        return new Vector3f(vector.getX() + x, vector.getY() + y, vector.getZ() + z);
    }

    public Vector3f add(float value) {
        return new Vector3f(value + x, value + y, value + z);
    }

    public Vector3f subtract(Vector3f vector) {
        return new Vector3f(vector.getX() - x, vector.getY() - y, vector.getZ() - z);
    }

    public Vector3f subtract(float value) {
        return new Vector3f(value - x, value - y, value - z);
    }

    public Vector3f multiply(Vector3f vector) {
        return new Vector3f(vector.getX() * x, vector.getY() * y, vector.getZ() * z);
    }

    public Vector3f multiply(float value) {
        return new Vector3f(value * x, value * y, value * z);
    }

    public Vector3f divide(Vector3f vector) {
        return new Vector3f(vector.getX() / x, vector.getY() / y, vector.getZ() / z);
    }

    public Vector3f divide(float value) {
        return new Vector3f(value / x, value / y, value / z);
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}
