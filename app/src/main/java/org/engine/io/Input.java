package org.engine.io;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.GLFW_REPEAT;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.joml.Vector2f;

public class Input {

    private static Vector2f mousePosition = new Vector2f(0.0f, 0.0f);
    private static Vector2f mousePositionDelta = new Vector2f(0.0f, 0.0f);

    private static Stack<
        AbstractMap.SimpleEntry<Integer, Boolean>
    > keyStateChangeStack = new Stack<
        AbstractMap.SimpleEntry<Integer, Boolean>
    >();

    private static Map<Integer, PressableState> keyStatesMap = new HashMap<
        Integer,
        PressableState
    >();

    private static Stack<
        AbstractMap.SimpleEntry<Integer, Boolean>
    > mouseButtonStateChangeStack = new Stack<
        AbstractMap.SimpleEntry<Integer, Boolean>
    >();
    private static Map<Integer, PressableState> mouseButtonStatesMap =
        new HashMap<Integer, PressableState>();

    public static boolean isKeyPressed(int glfwKey) {
        return keyStatesMap.get(glfwKey) == PressableState.PRESSED;
    }

    public static boolean isKeyJustPressed(int glfwKey) {
        return keyStatesMap.get(glfwKey) == PressableState.JUST_PRESSED;
    }

    public static boolean isKeyReleased(int glfwKey) {
        return keyStatesMap.get(glfwKey) == PressableState.RELEASED;
    }

    public static boolean isKeyJustReleased(int glfwKey) {
        return keyStatesMap.get(glfwKey) == PressableState.JUST_RELEASED;
    }

    public static boolean isMouseButtonPressed(int glfwButton) {
        return mouseButtonStatesMap.get(glfwButton) == PressableState.PRESSED;
    }

    public static boolean isMouseButtonJustPressed(int glfwButton) {
        return (
            mouseButtonStatesMap.get(glfwButton) == PressableState.JUST_PRESSED
        );
    }

    public static boolean isMouseButtonReleased(int glfwButton) {
        return mouseButtonStatesMap.get(glfwButton) == PressableState.RELEASED;
    }

    public static boolean isMouseButtonJustReleased(int glfwButton) {
        return (
            mouseButtonStatesMap.get(glfwButton) == PressableState.JUST_RELEASED
        );
    }

    public static Vector2f getMousePosition() {
        return mousePosition;
    }

    public static Vector2f getMousePositionDelta() {
        return mousePositionDelta;
    }

    public static void glfwKeyCallback(
        long glfwWindow,
        int key,
        int scancode,
        int action,
        int mods
    ) {
        keyStateChangeStack.push(
            new AbstractMap.SimpleEntry<Integer, Boolean>(
                key,
                action == GLFW_PRESS || action == GLFW_REPEAT ? true : false
            )
        );
    }

    public static void glfwMouseButtonCallback(
        long glfwWindow,
        int button,
        int action,
        int mods
    ) {
        keyStateChangeStack.push(
            new AbstractMap.SimpleEntry<Integer, Boolean>(
                button,
                action == GLFW_PRESS || action == GLFW_REPEAT ? true : false
            )
        );
    }

    public static void glfwMousePositionCallback(
        long glfwWindow,
        double xPos,
        double yPos
    ) {
        Vector2f newPosition = new Vector2f((float) xPos, (float) yPos);
        mousePositionDelta = newPosition.sub(mousePosition);
        mousePosition = newPosition;
    }

    public static void processPendingInput() {
        while (!keyStateChangeStack.empty()) {
            AbstractMap.SimpleEntry<Integer, Boolean> keyState =
                keyStateChangeStack.pop();

            if (
                keyStatesMap.get(keyState.getKey()) ==
                    PressableState.RELEASED &&
                keyState.getValue() == true
            ) {
                keyStatesMap.put(
                    keyState.getKey(),
                    PressableState.JUST_PRESSED
                );
            } else if (
                keyStatesMap.get(keyState.getKey()) == PressableState.PRESSED &&
                keyState.getValue() == false
            ) {
                keyStatesMap.put(
                    keyState.getKey(),
                    PressableState.JUST_RELEASED
                );
            } else {
                keyStatesMap.put(
                    keyState.getKey(),
                    keyState.getValue()
                        ? PressableState.PRESSED
                        : PressableState.RELEASED
                );
            }
        }

        while (!mouseButtonStateChangeStack.empty()) {
            AbstractMap.SimpleEntry<Integer, Boolean> mouseButtonState =
                mouseButtonStateChangeStack.pop();

            if (
                mouseButtonStatesMap.get(mouseButtonState.getKey()) ==
                    PressableState.RELEASED &&
                mouseButtonState.getValue() == true
            ) {
                mouseButtonStatesMap.put(
                    mouseButtonState.getKey(),
                    PressableState.JUST_PRESSED
                );
            } else if (
                mouseButtonStatesMap.get(mouseButtonState.getKey()) ==
                    PressableState.PRESSED &&
                mouseButtonState.getValue() == false
            ) {
                mouseButtonStatesMap.put(
                    mouseButtonState.getKey(),
                    PressableState.JUST_RELEASED
                );
            } else {
                mouseButtonStatesMap.put(
                    mouseButtonState.getKey(),
                    mouseButtonState.getValue()
                        ? PressableState.PRESSED
                        : PressableState.RELEASED
                );
            }
        }
    }
}
