package com.base.engine;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public class Shader {
    private int program;
    private HashMap<String, Integer> uniforms;

    public Shader() {
        program = glCreateProgram();
        uniforms = new HashMap<>();
        if (program == 0) {
            System.err.println("Shader creation failed: Could not find valid memory location in constructor");
            System.exit(1);
        }
    }
    public void bind() {
        glUseProgram(program);
    }

    public void addUniform(String uniform) {
        int uniformLocation = glGetUniformLocation(program, uniform);
        if (uniformLocation == 0xFFFFFFFF) {
            System.err.println("Error: Could not find uniform " + uniform);
            new Exception().printStackTrace();
            System.exit(1);
        }
        uniforms.put(uniform, uniformLocation);
    }

    public void addVertexShader(String code) {
        addProgram(code, GL_VERTEX_SHADER);
    }

    public void addGeometryShader(String code) {
        addProgram(code, GL_GEOMETRY_SHADER);
    }

    public void addFragmentShader(String code) {
        addProgram(code, GL_FRAGMENT_SHADER);
    }

    public void compileShader() {
        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(1);
        }
        glValidateProgram(program);
        if (glGetProgrami(program, GL_VALIDATE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(1);
        }
    }

    private void addProgram(String code, int type) {
        int shader = glCreateShader(type);
        if (shader == 0) {
            System.err.println("Shader creation failed: Could not find valid memory location when adding shader");
            System.exit(1);
        }
        glShaderSource(shader, code);
        glCompileShader(shader);
        if (glGetShaderi(shader, GL_COMPILE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(shader, 1024));
            System.exit(1);
        }
        glAttachShader(program, shader);
    }

    public void setUniformi(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }
    public void setUniformf(String uniformName, float value) {
        glUniform1f(uniforms.get(uniformName), value);
    }
    public void setUniform(String uniformName, Vector3f vector) {
        glUniform3f(uniforms.get(uniformName), vector.getX(), vector.getY(), vector.getZ());
    }
    public void setUniform(String uniformName, Matrix4f matrix) {
        glUniformMatrix4fv(uniforms.get(uniformName), true, Util.createFlippedBuffer(matrix));
    }
}
