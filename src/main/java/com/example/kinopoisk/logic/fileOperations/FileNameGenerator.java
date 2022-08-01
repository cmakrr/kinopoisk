package com.example.kinopoisk.logic.fileOperations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameGenerator {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

    public String generateFileName(String extension){
        return generateName().concat(".").concat(extension);
    }

    private String generateName(){
        return dateFormat.format(new Date());
    }
}
