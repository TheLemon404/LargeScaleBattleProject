package org.engine.graphics;

import java.util.Arrays;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Vertex {

    public Vector3f position = new Vector3f(0, 0, 0);
    public Vector3f normal = new Vector3f(0, 0, 0);
    public Vector2f uv = new Vector2f(0, 0);

    public static int BYTES = Vector3f.BYTES * 2 + Vector2f.BYTES;

    public static float[] flatten(Vertex[] vertices) {
        float[] data = new float[vertices.length * 8];
        for (int i = 0; i < vertices.length; i++) {
            data[i * 8] = vertices[i].position.x;
            data[i * 8 + 1] = vertices[i].position.y;
            data[i * 8 + 2] = vertices[i].position.z;
            data[i * 8 + 3] = vertices[i].normal.x;
            data[i * 8 + 4] = vertices[i].normal.y;
            data[i * 8 + 5] = vertices[i].normal.z;
            data[i * 8 + 6] = vertices[i].uv.x;
            data[i * 8 + 7] = vertices[i].uv.y;
        }
        return data;
    }

    public static float[] extractPositionData(Vertex[] vertices) {
        float[] data = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            data[i * 3] = vertices[i].position.x;
            data[i * 3 + 1] = vertices[i].position.y;
            data[i * 3 + 2] = vertices[i].position.z;
        }
        return data;
    }

    public static float[] extractNormalData(Vertex[] vertices) {
        float[] data = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {
            data[i * 3] = vertices[i].normal.x;
            data[i * 3 + 1] = vertices[i].normal.y;
            data[i * 3 + 2] = vertices[i].normal.z;
        }
        return data;
    }

    public static float[] extractUVData(Vertex[] vertices) {
        float[] data = new float[vertices.length * 2];
        for (int i = 0; i < vertices.length; i++) {
            data[i * 2] = vertices[i].uv.x;
            data[i * 2 + 1] = vertices[i].uv.y;
        }
        return data;
    }

    public Vertex(Vector3f position, Vector3f normal, Vector2f uv) {
        this(position, normal);
        this.uv = uv;
    }

    public Vertex(Vector3f position, Vector2f uv) {
        this(position);
        this.uv = uv;
    }

    public Vertex(Vector3f position, Vector3f normal) {
        this(position);
        this.normal = normal;
    }

    public Vertex(Vector3f position) {
        this.position = position;
    }
}
