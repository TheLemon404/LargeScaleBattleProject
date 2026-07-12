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

    public static Vertex[] generateCubeVertices() {
        // Cube vertices (side length = 1, centered at origin)
        Vertex[] vertices = new Vertex[24]; // 4 vertices per face × 6 faces

        // Helper to make vertex with position, normal, and UV
        int i = 0;

        // === FRONT (Z = +0.5) ===
        Vector3f frontNormal = new Vector3f(0, 0, 1);
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, -0.5f, 0.5f),
            frontNormal,
            new Vector2f(0.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, -0.5f, 0.5f),
            frontNormal,
            new Vector2f(1.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, 0.5f, 0.5f),
            frontNormal,
            new Vector2f(1.0f, 1.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, 0.5f, 0.5f),
            frontNormal,
            new Vector2f(0.0f, 1.0f)
        );

        // === BACK (Z = -0.5) ===
        Vector3f backNormal = new Vector3f(0, 0, -1);
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, -0.5f, -0.5f),
            backNormal,
            new Vector2f(1.0f, 0.0f)
        ); // flipped u for correct orientation
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, 0.5f, -0.5f),
            backNormal,
            new Vector2f(1.0f, 1.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, 0.5f, -0.5f),
            backNormal,
            new Vector2f(0.0f, 1.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, -0.5f, -0.5f),
            backNormal,
            new Vector2f(0.0f, 0.0f)
        );

        // === RIGHT (X = +0.5) ===
        Vector3f rightNormal = new Vector3f(1, 0, 0);
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, -0.5f, -0.5f),
            rightNormal,
            new Vector2f(0.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, -0.5f, 0.5f),
            rightNormal,
            new Vector2f(1.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, 0.5f, 0.5f),
            rightNormal,
            new Vector2f(1.0f, 1.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, 0.5f, -0.5f),
            rightNormal,
            new Vector2f(0.0f, 1.0f)
        );

        // === LEFT (X = -0.5) ===
        Vector3f leftNormal = new Vector3f(-1, 0, 0);
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, -0.5f, 0.5f),
            leftNormal,
            new Vector2f(0.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, -0.5f, -0.5f),
            leftNormal,
            new Vector2f(1.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, 0.5f, -0.5f),
            leftNormal,
            new Vector2f(1.0f, 1.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, 0.5f, 0.5f),
            leftNormal,
            new Vector2f(0.0f, 1.0f)
        );

        // === TOP (Y = +0.5) ===
        Vector3f topNormal = new Vector3f(0, 1, 0);
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, 0.5f, -0.5f),
            topNormal,
            new Vector2f(0.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, 0.5f, -0.5f),
            topNormal,
            new Vector2f(1.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, 0.5f, 0.5f),
            topNormal,
            new Vector2f(1.0f, 1.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, 0.5f, 0.5f),
            topNormal,
            new Vector2f(0.0f, 1.0f)
        );

        // === BOTTOM (Y = -0.5) ===
        Vector3f bottomNormal = new Vector3f(0, -1, 0);
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, -0.5f, -0.5f),
            bottomNormal,
            new Vector2f(0.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(-0.5f, -0.5f, 0.5f),
            bottomNormal,
            new Vector2f(1.0f, 0.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, -0.5f, 0.5f),
            bottomNormal,
            new Vector2f(1.0f, 1.0f)
        );
        vertices[i++] = new Vertex(
            new Vector3f(0.5f, -0.5f, -0.5f),
            bottomNormal,
            new Vector2f(0.0f, 1.0f)
        );

        return vertices;
    }

    public static int[] generateCubeIndices() {
        return new int[] {
            // FRONT
            0,
            1,
            2,
            2,
            3,
            0,
            // BACK
            4,
            5,
            6,
            6,
            7,
            4,
            // RIGHT
            8,
            9,
            10,
            10,
            11,
            8,
            // LEFT
            12,
            13,
            14,
            14,
            15,
            12,
            // TOP
            16,
            17,
            18,
            18,
            19,
            16,
            // BOTTOM
            20,
            21,
            22,
            22,
            23,
            20,
        };
    }
}
