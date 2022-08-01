package com.example.kinopoisk.logic.fileOperations;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOperations {
    private static final Logger logger = Logger.getLogger(FileOperations.class.getName());

    public static boolean trySaveMultipartFile(Path path, MultipartFile multipartFile){
        boolean wasSaved;
        try {
            wasSaved = trySaveToFile(path, multipartFile.getBytes());
        } catch (IOException exception){
            wasSaved = false;
            logger.log(Level.WARNING,"could not get bytes from multipart file",exception);
        }
        return wasSaved;
    }

    public static boolean trySaveToFile(Path path, byte[] bytes){
        boolean wasSaved = true;
        try{
            Files.write(path,bytes);
        } catch (IOException exception){
            wasSaved = false;
            logger.log(Level.WARNING,"could not save correctly",exception);
        }
        return wasSaved;
    }

    public static boolean tryDeleteFile(Path path){
        boolean wasDeleted;
        try {
            wasDeleted = Files.deleteIfExists(path);
        } catch(IOException exception){
            wasDeleted = false;
            logger.log(Level.WARNING,"could not delete file",exception);
        }
        return wasDeleted;
    }
}
