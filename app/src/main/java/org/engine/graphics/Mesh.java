package org.engine.graphics;

import static org.lwjgl.opengl.GL46C.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;

public class Mesh {

    public Material material;

    public int vao = -1;
    public int vbo = -1;
    public int ebo = -1;
    public int vertexCount = 0;
    public int indexCount = 0;

    public void storeVertices(Vertex[] vertices, int[] indices) {
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        FloatBuffer pBuffer = BufferUtils.createFloatBuffer(
            vertices.length * Vertex.BYTES
        );
        pBuffer.put(Vertex.flatten(vertices)).flip();

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, pBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.BYTES, 0);
        glVertexAttribPointer(
            1,
            3,
            GL_FLOAT,
            false,
            Vertex.BYTES,
            3 * Float.BYTES
        );
        glVertexAttribPointer(
            2,
            2,
            GL_FLOAT,
            false,
            Vertex.BYTES,
            5 * Float.BYTES
        );
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        IntBuffer eBuffer = BufferUtils.createIntBuffer(indices.length);
        eBuffer.put(indices).flip();

        ebo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, eBuffer, GL_STATIC_DRAW);

        vertexCount = vertices.length;
        indexCount = indices.length;
    }
}
