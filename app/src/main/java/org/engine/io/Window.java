package org.engine.io;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL46.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;
import java.util.function.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

public class Window {

    private int width, height;
    private long glfwWindow;

    public Window(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Window() {
        this(1200, 800);
    }

    public void open(String title) throws IllegalStateException {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) throw new IllegalStateException(
            "unable to initialize glfw"
        );

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        glfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (glfwWindow == NULL) throw new IllegalStateException(
            "Failed to create glfw window"
        );

        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(glfwWindow, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(
                glfwWindow,
                (vidmode.width() - pWidth.get(0)) / 2,
                (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(glfwWindow);

        glfwSetKeyCallback(glfwWindow, Input::glfwKeyCallback);
        glfwSetMouseButtonCallback(glfwWindow, Input::glfwMouseButtonCallback);
        glfwSetCursorPosCallback(glfwWindow, Input::glfwMousePositionCallback);

        glfwSwapInterval(1);
        glfwShowWindow(glfwWindow);
    }

    public boolean ShouldClose() {
        return glfwWindowShouldClose(glfwWindow);
    }

    public void loop(Consumer<Float> loopFunction) {
        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

        while (!ShouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwSwapBuffers(glfwWindow);
            loopFunction.accept(1.0f);
            glfwPollEvents();
        }
    }

    public void close() {
        GL.destroy();
        glfwDestroyWindow(glfwWindow);
    }
}
