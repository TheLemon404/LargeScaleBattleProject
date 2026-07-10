package org.engine.graphics;

import static org.lwjgl.opengl.GL46C.*;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Shader {

    public final String name;
    private int programId = -1;
    private int vertexShaderId = -1;
    private int fragmentShaderId = -1;
    public String vertexSourceFile;
    public String fragmentSourceFile;

    public Shader(
        String name,
        String vertexSourceFile,
        String fragmentSourceFile
    ) {
        this.name = name;
        this.vertexSourceFile = vertexSourceFile;
        this.fragmentSourceFile = fragmentSourceFile;
    }

    public void Compile() {}

    public int GetUniformLocation(String uniform) {
        return glGetUniformLocation(programId, uniform);
    }

    public void SetShaderUniformFloat(int location, float val) {
        glUniform1f(location, val);
    }

    public void SetShaderUniformInt(int location, int val) {
        glUniform1i(location, val);
    }

    public void SetShaderUniformVector2f(int location, Vector2f val) {
        glUniform2f(location, val.x, val.y);
    }

    public void SetShaderUniformVector3f(int location, Vector3f val) {
        glUniform3f(location, val.x, val.y, val.z);
    }

    public void SetShaderUniformVector4f(int location, Vector4f val) {
        glUniform4f(location, val.x, val.y, val.z, val.w);
    }
}
