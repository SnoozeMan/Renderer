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
        float x_ = vector.getZ() * y - vector.getY() * z;
        float y_ = vector.getX() * z - vector.getZ() * x;
        float z_ = vector.getY() * x - vector.getX() * y;
        return new Vector3f(x_, y_, z_);
    }

    public Vector3f normalize() {
        float length = length();
        x /= length;
        y /= length;
        z /= length;
        return this;
    }

    public Vector3f rotate(float angle, Vector3f axis) {
        float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));
        float rX = axis.getX() * sinHalfAngle;
        float rY = axis.getY() * sinHalfAngle;
        float rZ = axis.getZ() * sinHalfAngle;
        float rW = cosHalfAngle;
        Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = rotation.conjugate();
        Quaternion w = rotation.multiply(this).multiply(conjugate);
        x = w.getX();
        y = w.getY();
        z = w.getZ();
        return this;
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
