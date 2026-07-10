package org.engine.scene;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform {
    public Vector3f position = new Vector3f();
    public Quaternionf rotation = new Quaternionf().identity();
    public Vector3f scale = new Vector3f(1.0f, 1.0f, 1.0f);

    private Matrix4f matrix = new Matrix4f();

    public Transform add(Transform other) {
        Transform result = new Transform();
        result.position = position.add(other.position);
        result.rotation = rotation.add(other.rotation);
        result.scale = scale.add(other.scale);
        return result;
    }

    public void computeMatrix() {
        matrix = matrix.identity().translate(position).rotate(rotation).scale(scale);
    }

    public Matrix4f getMatrix() {
        return matrix;
    }
}
