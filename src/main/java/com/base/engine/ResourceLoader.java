package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ResourceLoader {

    public static String loadShader(String fileName) {
        StringBuilder shaderSource = new StringBuilder();
        try {
            BufferedReader shaderReader = new BufferedReader(new FileReader(ResourceLoader.class.getClassLoader().getResource(fileName).getPath()));
            String line;
            while((line = shaderReader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
            shaderReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shaderSource.toString();
    }

    public static Mesh loadMesh(String fileName) {
        String[] splitArray = fileName.split("\\.");
        String extension = splitArray[splitArray.length - 1];
        if (!extension.equals("obj")) {
            System.err.println("Error: File format not supported for mesh data: " + extension);
            new Exception().printStackTrace();
            System.exit(1);
        }
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        try {
            BufferedReader meshReader = new BufferedReader(new FileReader(ResourceLoader.class.getClassLoader().getResource(fileName).getPath()));
            String line;
            while((line = meshReader.readLine()) != null) {
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);
                if (tokens.length == 0 || tokens[0].equals("#")) {
                    continue;
                }
                if (tokens[0].equals("v")) {
                    vertices.add(new Vertex(new Vector3f(Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]), Float.parseFloat(tokens[3]))));
                }
                else if (tokens[0].equals("f")) {
                    indices.add(Integer.parseInt(tokens[1]) - 1);
                    indices.add(Integer.parseInt(tokens[2]) - 1);
                    indices.add(Integer.parseInt(tokens[3]) - 1);
                }
            }
            meshReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Mesh resultingMesh = new Mesh();
        Vertex[] vertexData = new Vertex[vertices.size()];
        vertices.toArray(vertexData);
        Integer[] indexData = new Integer[indices.size()];
        indices.toArray(indexData);
        resultingMesh.addVertices(vertexData, Util.toIntArray(indexData));
        return resultingMesh;
    }
}
