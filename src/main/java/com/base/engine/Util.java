package com.base.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Util {
    public static FloatBuffer createFloatBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);
        for (int i = 0; i < vertices.length; i++) {
            buffer.put(vertices[i].getPosition().getX());
            buffer.put(vertices[i].getPosition().getY());
            buffer.put(vertices[i].getPosition().getZ());
        }
        buffer.flip();
        return buffer;
    }
}