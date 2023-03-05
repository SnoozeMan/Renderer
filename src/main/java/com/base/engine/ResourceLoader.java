package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
}
