package com.base.engine;

public class Matrix4f {
    private float[][] matrix;

    public Matrix4f() {
        this.matrix = new float[4][4];
    }

    public Matrix4f initializeIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == i) {
                    matrix[i][j] = 1;
                }
                else {
                    matrix[i][j] = 0;
                }
            }
        }
        return this;
    }

    public Matrix4f multiply(Matrix4f matrix2) {
        Matrix4f resultingMatrix = new Matrix4f();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                resultingMatrix.set(i, j, matrix[i][0] * matrix2.get(0, j) +
                        matrix[i][1] * matrix2.get(1, j) +
                        matrix[i][2] * matrix2.get(2, j) +
                        matrix[i][3] * matrix2.get(3, j));
            }
        }
        return resultingMatrix;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public float get(int x, int y) {
        return matrix[x][y];
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public void set(int x, int y, float value) {
        matrix[x][y] = value;
    }
}
