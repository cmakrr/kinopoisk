package com.example.kinopoisk.logic.fileOperations;

import org.springframework.core.io.ClassPathResource;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathCreator {
    public Path createResourcesPath(String resourcesDirectory, String fileName){
        String directoryPath = new ClassPathResource(resourcesDirectory).getPath();
        String filePath = directoryPath+fileName;
        return Paths.get(filePath);
    }
}
