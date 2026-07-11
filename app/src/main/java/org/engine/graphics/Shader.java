package org.engine.graphics;

import static org.lwjgl.opengl.GL46C.*;

import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

public class Shader {

    public final String name;
    private int programId = -1;
    private int vertexShaderId = -1;
    private int fragmentShaderId = -1;
    public final String vertexSourcePath;
    public final String fragmentSourcePath;

    private Map<String, Integer> cachedShaderLocations = new HashMap<>();

    public Shader(
        String name,
        String vertexSourcePath,
        String fragmentSourcePath
    ) {
        this.name = name;
        this.vertexSourcePath = vertexSourcePath;
        this.fragmentSourcePath = fragmentSourcePath;
    }

    public void compile() throws RuntimeException {
        String vertexSource = "";
        String fragmentSource = "";
        try {
            vertexSource = Files.readString(Path.of(vertexSourcePath));
            fragmentSource = Files.readString(Path.of(fragmentSourcePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        programId = glCreateProgram();
        if (programId == 0) throw new RuntimeException(
            "Failed to create shader program at shader: " + name
        );

        vertexShaderId = glCreateShader(GL_VERTEX_SHADER);
        if (vertexShaderId == 0) throw new RuntimeException(
            "Failed to create vertex shader at shader: " + name
        );
        glShaderSource(vertexShaderId, vertexSource);
        glCompileShader(vertexShaderId);
        if (glGetShaderi(vertexShaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException(
                "Error compiling shader: " +
                    name +
                    ": " +
                    glGetShaderInfoLog(vertexShaderId)
            );
        }

        fragmentShaderId = glCreateShader(GL_FRAGMENT_SHADER);
        if (fragmentShaderId == 0) throw new RuntimeException(
            "Failed to create fragment shader at shader: " + name
        );
        glShaderSource(fragmentShaderId, fragmentSource);
        glCompileShader(fragmentShaderId);
        if (glGetShaderi(fragmentShaderId, GL_COMPILE_STATUS) == GL_FALSE) {
            throw new RuntimeException(
                "Error compiling shader: " +
                    name +
                    ": " +
                    glGetShaderInfoLog(fragmentShaderId)
            );
        }

        glAttachShader(programId, vertexShaderId);
        glAttachShader(programId, fragmentShaderId);
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE) {
            throw new RuntimeException(
                "Error linking shader: " +
                    name +
                    ": " +
                    glGetProgramInfoLog(programId)
            );
        }

        glDetachShader(programId, vertexShaderId);
        glDetachShader(programId, fragmentShaderId);
        glDeleteShader(vertexShaderId);
        glDeleteShader(fragmentShaderId);
    }

    public void use() {
        glUseProgram(programId);
    }

    public void stopUsing() {
        glUseProgram(0);
    }

    public void delete() {
        stopUsing();
        glDeleteProgram(programId);
    }

    public ArrayList<String> getAllUniformNames() {
        int uniformCount = glGetProgrami(programId,GL_ACTIVE_UNIFORMS);
        ArrayList<String> names = new ArrayList<>(uniformCount);
        for(int i = 0; i < uniformCount; i++) {
            names.set(i, glGetActiveUniformName(programId, i));
        }
        return names;
    }

    public int getUniformLocation(String uniform) {
        if (
            cachedShaderLocations.containsKey(uniform)
        ) return cachedShaderLocations.get(uniform);

        int location = glGetUniformLocation(programId, uniform);
        cachedShaderLocations.put(uniform, location);
        return location;
    }

    public void setShaderUniformFloat(int location, float val) {
        glUniform1f(location, val);
    }

    public void setShaderUniformInt(int location, int val) {
        glUniform1i(location, val);
    }

    public void setShaderUniformVector2f(int location, Vector2f val) {
        glUniform2f(location, val.x, val.y);
    }

    public void setShaderUniformVector3f(int location, Vector3f val) {
        glUniform3f(location, val.x, val.y, val.z);
    }

    public void setShaderUniformVector4f(int location, Vector4f val) {
        glUniform4f(location, val.x, val.y, val.z, val.w);
    }
}
