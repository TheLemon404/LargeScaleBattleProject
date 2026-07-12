package org.engine.graphics;

import static org.lwjgl.opengl.GL46C.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.engine.assets.Asset;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture extends Asset {

    public int id = -1;
    public final String path;
    public int filter = GL_NEAREST;

    public Texture(String name, String path) {
        super(name);

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer buffer = STBImage.stbi_load(path, w, h, channels, 4);
            if (buffer == null) {
                throw new RuntimeException(
                    "Image file: " +
                        path +
                        " not loaded due to: " +
                        STBImage.stbi_failure_reason()
                );
            }

            int width = w.get();
            int height = h.get();
            generateTexture(width, height, buffer);

            STBImage.stbi_image_free(buffer);
        }

        this.path = path;
    }

    public Texture(int width, int height, ByteBuffer buffer) {
        super();
        this.path = "";
        generateTexture(width, height, buffer);
    }

    private void generateTexture(int width, int height, ByteBuffer buffer) {
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexImage2D(
            GL_TEXTURE_2D,
            0,
            GL_RGBA,
            width,
            height,
            0,
            GL_RGBA,
            GL_UNSIGNED_BYTE,
            buffer
        );
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public void setFilter(int filter) {
        this.filter = filter;
        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
    }
}
