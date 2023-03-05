package com.base.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Util {
    public static FloatBuffer createFloatBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer createIntBuffer(int size) {
        return BufferUtils.createIntBuffer(size);
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

    public static FloatBuffer createFlippedBuffer(Matrix4f matrix) {
        FloatBuffer buffer = createFloatBuffer(4 * 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buffer.put(matrix.get(i, j));
            }
        }
        buffer.flip();
        return buffer;
    }

    public static IntBuffer createFlippedBuffer(int... values) {
        IntBuffer buffer = createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }

    public static String[] removeEmptyStrings(String[] strings) {
        ArrayList<Integer> nonEmptyIndexes = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].equals("")) {
                nonEmptyIndexes.add(i);
            }
        }
        String[] nonEmptyStrings = new String[nonEmptyIndexes.size()];
        for (int i = 0; i < nonEmptyStrings.length; i++) {
            nonEmptyStrings[i] = strings[nonEmptyIndexes.get(i)];
        }
        return nonEmptyStrings;
    }

    public static int[] toIntArray(Integer[] integers) {
        int[] ints = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            ints[i] = integers[i].intValue();
        }
        return ints;
    }
}
