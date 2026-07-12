package org.engine.graphics;

import static org.lwjgl.opengl.GL46C.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Material {

    public final String name;
    public final Shader shader;

    private Map<String, Object> params = new HashMap<>();
    private Map<Integer, Texture> textureSlots = new HashMap<>();

    public Material(String name, Shader shader) {
        this.name = name;
        this.shader = shader;
        for (String uniformName : shader.getAllUniformNames()) {
            params.put(uniformName, null);
        }
    }

    public void uploadParams() {
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (param.getValue() != null) setShaderUniform(
                param.getKey(),
                param.getValue()
            );
        }
    }

    public void bindTextureSlots() {
        for (Map.Entry<Integer, Texture> slot : textureSlots.entrySet()) {
            glActiveTexture(slot.getKey() + 33984);
            glBindTexture(GL_TEXTURE_2D, slot.getValue().id);
        }
    }

    public Object getParam(String paramName) throws Exception {
        if (!params.containsKey(paramName)) throw new Exception(
            "Material: " + name + " does not contain a parameter: " + paramName
        );

        return params.get(paramName);
    }

    public Set<String> getParamNames() {
        return params.keySet();
    }

    private <T> void setShaderUniform(String paramName, T value)
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
        else if (value instanceof Matrix4f) shader.setShaderUniformMatrix4f(
            location,
            (Matrix4f) value
        );
        else {
            throw new RuntimeException(
                "The shaders do not support uniform uploads of type: " +
                    value.getClass()
            );
        }
    }

    public <T> void setParam(String paramName, T value) {
        if (shader.getUniformLocation(paramName) == -1) return;

        params.put(paramName, value);
    }

    public void setTextureSlot(int slot, Texture texture) {
        textureSlots.put(slot, texture);
    }
}
