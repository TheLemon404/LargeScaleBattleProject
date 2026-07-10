package org.engine.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Material {

    public final Shader shader;

    public Material(Shader shader) {
        this.shader = shader;
    }

    public <T> void setParam(String paramName, T value)
        throws RuntimeException {
        int location = shader.getUniformLocation(paramName);
        if (location == -1) throw new RuntimeException(
            "The shader: " +
                shader.name +
                " does not contain uniform parameter: " +
                paramName
        );
        if (value instanceof Float) shader.setShaderUniformFloat(
            location,
            (Float) value
        );
        else if (value instanceof Integer) shader.setShaderUniformInt(
            location,
            (Integer) value
        );
        else if (value instanceof Vector2f) shader.setShaderUniformVector2f(
            location,
            (Vector2f) value
        );
        else if (value instanceof Vector3f) shader.setShaderUniformVector3f(
            location,
            (Vector3f) value
        );
        else if (value instanceof Vector4f) shader.setShaderUniformVector4f(
            location,
            (Vector4f) value
        );
        else {
            throw new RuntimeException(
                "The shaders do not support uniform uploads of type: " +
                    value.getClass()
            );
        }
    }
}
