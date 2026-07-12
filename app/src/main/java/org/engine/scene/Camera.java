package org.engine.scene;

import org.engine.io.Window;
import org.joml.Matrix4f;

public class Camera extends Entity {

    public static Camera activeCamera;

    public float fov = 40.0f;
    public float near = 0.01f;
    public float far = 1000.0f;

    private Matrix4f viewMatrix = new Matrix4f();
    private Matrix4f projectionMatrix = new Matrix4f();

    public Camera() {
        if (activeCamera == null) activeCamera = this;
    }

    public void recomputeMatrices(Window window) {
        viewMatrix = viewMatrix
            .identity()
            .translate(getGlobalTransform().position)
            .rotate(getGlobalTransform().rotation);
        projectionMatrix = projectionMatrix
            .identity()
            .perspective(
                fov,
                (float) window.getWidth() / (float) window.getHeight(),
                near,
                far
            );
    }

    public void makeActiveCamera() {
        activeCamera = this;
    }

    public Matrix4f getView() {
        return viewMatrix;
    }

    public Matrix4f getProjection() {
        return projectionMatrix;
    }
}
