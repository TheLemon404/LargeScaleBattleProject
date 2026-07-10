package org.engine.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Material {

    public Shader shader;

    public <T> void SetParam(String paramName, T value)
        throws RuntimeException {
        int location = shader.GetUniformLocation(paramName);
        if (location == -1) throw new RuntimeException(
            "The shader: " +
                shader.name +
                " does not contain uniform parameter: " +
                paramName
        );
        if (value instanceof Float) shader.SetShaderUniformFloat(
            location,
            (Float) value
        );
        else if (value instanceof Integer) shader.SetShaderUniformInt(
            location,
            (Integer) value
        );
        else if (value instanceof Vector2f) shader.SetShaderUniformVector2f(
            location,
            (Vector2f) value
        );
        else if (value instanceof Vector3f) shader.SetShaderUniformVector3f(
            location,
            (Vector3f) value
        );
        else if (value instanceof Vector4f) shader.SetShaderUniformVector4f(
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
