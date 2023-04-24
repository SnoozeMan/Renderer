package com.base.engine;

public class Matrix4f {
    private float[][] m;

    public Matrix4f() {
        this.m = new float[4][4];
    }

    public Matrix4f initializeIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i) {
                    m[i][j] = 1;
                }
                else {
                    m[i][j] = 0;
                }
            }
        }
        return this;
    }

    public Matrix4f initializeTranslation(float x, float y, float z) {
        m[0][0] = 1;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = x;
        m[1][0] = 0;    m[1][1] = 1;    m[1][2] = 0;    m[1][3] = y;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = 1;    m[2][3] = z;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;
        return this;
    }

    public Matrix4f initializeRotation(float x, float y, float z) {
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
        Matrix4f rz = new Matrix4f();
        x = (float)Math.toRadians(x);
        y = (float)Math.toRadians(y);
        z = (float)Math.toRadians(z);

        rz.m[0][0] = (float)Math.cos(z);    rz.m[0][1] = -(float)Math.sin(z);   rz.m[0][2] = 0;                     rz.m[0][3] = 0;
        rz.m[1][0] = (float)Math.sin(z);    rz.m[1][1] = (float)Math.cos(z);    rz.m[1][2] = 0;                     rz.m[1][3] = 0;
        rz.m[2][0] = 0;                     rz.m[2][1] = 0;                     rz.m[2][2] = 1;                     rz.m[2][3] = 0;
        rz.m[3][0] = 0;                     rz.m[3][1] = 0;                     rz.m[3][2] = 0;                     rz.m[3][3] = 1;

        rx.m[0][0] = 1;                     rx.m[0][1] = 0;                     rx.m[0][2] = 0;                     rx.m[0][3] = 0;
        rx.m[1][0] = 0;                     rx.m[1][1] = (float)Math.cos(x);    rx.m[1][2] = -(float)Math.sin(x);   rx.m[1][3] = 0;
        rx.m[2][0] = 0;                     rx.m[2][1] = (float)Math.sin(x);    rx.m[2][2] = (float)Math.cos(x);    rx.m[2][3] = 0;
        rx.m[3][0] = 0;                     rx.m[3][1] = 0;                     rx.m[3][2] = 0;                     rx.m[3][3] = 1;

        ry.m[0][0] = (float)Math.cos(y);    ry.m[0][1] = 0;                     ry.m[0][2] = (float)Math.sin(y);    ry.m[0][3] = 0;
        ry.m[1][0] = 0;                     ry.m[1][1] = 1;                     ry.m[1][2] = 0;                     ry.m[1][3] = 0;
        ry.m[2][0] = -(float)Math.sin(y);   ry.m[2][1] = 0;                     ry.m[2][2] = (float)Math.cos(y);    ry.m[2][3] = 0;
        ry.m[3][0] = 0;                     ry.m[3][1] = 0;                     ry.m[3][2] = 0;                     ry.m[3][3] = 1;

        m = rz.multiply(ry.multiply(rx)).getM();
        return this;
    }

    public Matrix4f initializeScale(float x, float y, float z) {
        m[0][0] = x;    m[0][1] = 0;    m[0][2] = 0;    m[0][3] = 0;
        m[1][0] = 0;    m[1][1] = y;    m[1][2] = 0;    m[1][3] = 0;
        m[2][0] = 0;    m[2][1] = 0;    m[2][2] = z;    m[2][3] = 0;
        m[3][0] = 0;    m[3][1] = 0;    m[3][2] = 0;    m[3][3] = 1;
        return this;
    }

    public Matrix4f initializeProjection(float fov, float width, float height, float zNear, float zFar) {
        float aspectRatio = width / height;
        float tanHalfFOV = (float)Math.tan(Math.toRadians(fov / 2));
        float zRange = zNear - zFar;
        m[0][0] = 1/(tanHalfFOV*aspectRatio); m[0][1] = 0;            m[0][2] = 0;                    m[0][3] = 0;
        m[1][0] = 0;                          m[1][1] = 1/tanHalfFOV; m[1][2] = 0;                    m[1][3] = 0;
        m[2][0] = 0;                          m[2][1] = 0;            m[2][2] = (-zNear-zFar)/zRange; m[2][3] = 2*zFar*zNear/zRange;
        m[3][0] = 0;                          m[3][1] = 0;            m[3][2] = 1;                    m[3][3] = 0;
        return this;
    }

    public Matrix4f initializeCamera(Vector3f forward, Vector3f up) {
        Vector3f f = forward;
        f.normalize();
        Vector3f r = up; // Right
        r.normalize();
        r = r.cross(f);
        Vector3f u = f.cross(r); // Up
        m[0][0] = r.getX(); m[0][1] = r.getY(); m[0][2] = r.getZ(); m[0][3] = 0;
        m[1][0] = u.getX(); m[1][1] = u.getY(); m[1][2] = u.getZ(); m[1][3] = 0;
        m[2][0] = f.getX(); m[2][1] = f.getY(); m[2][2] = f.getZ(); m[2][3] = 0;
        m[3][0] = 0;        m[3][1] = 0;        m[3][2] = 0;        m[3][3] = 1;
        return this;
    }

    public Matrix4f multiply(Matrix4f matrix2) {
        Matrix4f resultingMatrix = new Matrix4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                resultingMatrix.set(i, j, m[i][0] * matrix2.get(0, j) +
                        m[i][1] * matrix2.get(1, j) +
                        m[i][2] * matrix2.get(2, j) +
                        m[i][3] * matrix2.get(3, j));
            }
        }
        return resultingMatrix;
    }

    public float[][] getM() {
        return m;
    }

    public float get(int x, int y) {
        return m[x][y];
    }

    public void setM(float[][] m) {
        this.m = m;
    }

    public void set(int x, int y, float value) {
        m[x][y] = value;
    }
}
