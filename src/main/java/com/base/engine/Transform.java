package com.base.engine;

public class Transform {
    private static Camera camera;
    private static float zNear;
    private static float zFar;
    private static float width;
    private static float height;
    private static float fov;
    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform() {
        translation = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
        scale = new Vector3f(1, 1, 1);
    }

    public Matrix4f getTransformation() {
        Matrix4f translationM = new Matrix4f().initializeTranslation(translation.getX(), translation.getY(), translation.getZ());
        Matrix4f rotationM = new Matrix4f().initializeRotation(rotation.getX(), rotation.getY(), rotation.getZ());
        Matrix4f scaleM = new Matrix4f().initializeScale(scale.getX(), scale.getY(), scale.getZ());
        return translationM.multiply(rotationM.multiply(scaleM));
    }

    public Matrix4f getProjectedTransformation() {
        Matrix4f transformationMatrix = getTransformation();
        Matrix4f projectionMatrix = new Matrix4f().initializeProjection(fov, width, height, zNear, zFar);
        Matrix4f cameraRotationMatrix = new Matrix4f().initializeCamera(camera.getForward(), camera.getUp());
        float x = camera.getPosition().getX();
        float y = camera.getPosition().getY();
        float z = camera.getPosition().getZ();
        Matrix4f cameraTranslationMatrix = new Matrix4f().initializeTranslation(-x, -y, -z);
        return projectionMatrix.multiply(cameraRotationMatrix.multiply(cameraTranslationMatrix.multiply(transformationMatrix)));
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public static void setProjection(float fov, float width, float height, float zNear, float zFar) {
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zNear = zNear;
        Transform.zFar = zFar;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public void setTranslation(float x, float y, float z) {
        this.translation = new Vector3f(x, y, z);
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation = new Vector3f(x, y, z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void setScale(float x, float y, float z) {
        this.scale = new Vector3f(x, y, z);
    }

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        Transform.camera = camera;
    }
}
