package com.example.kinopoisk.configurations;

import com.example.kinopoisk.logic.fileOperations.FileNameGenerator;
import com.example.kinopoisk.logic.fileOperations.PathCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageOperationsConfiguration {
    @Bean
    public FileNameGenerator fileNameGenerator(){
        return new FileNameGenerator();
    }

    @Bean
    public PathCreator pathCreator(){
        return new PathCreator();
    }
}
