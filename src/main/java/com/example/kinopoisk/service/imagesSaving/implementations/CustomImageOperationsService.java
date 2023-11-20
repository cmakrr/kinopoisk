package com.example.kinopoisk.service.imagesSaving.implementations;

import com.example.kinopoisk.logic.fileOperations.FileNameGenerator;
import com.example.kinopoisk.logic.fileOperations.FileOperations;
import com.example.kinopoisk.logic.fileOperations.PathCreator;
import com.example.kinopoisk.service.imagesSaving.interfaces.ImageOperationsService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:/properties/paths.properties")
@RequiredArgsConstructor
public class CustomImageOperationsService implements ImageOperationsService {

    private final FileNameGenerator nameGenerator;
    private final PathCreator pathCreator;
    @Value("${imagesResourcesPath}")
    private String resourcesDirectory;

    @Override
    public List<String> saveImages(MultipartFile[] images){
        List<String> imagesNames = new ArrayList<>();
        for(MultipartFile image : images){
            saveImage(image).ifPresent(imagesNames::add);
        }
        return imagesNames;
    }

    @Override
    public Optional<String> saveImage(MultipartFile multipartFile){
        String name = generateFileName(multipartFile);
        Path filePath = pathCreator.createResourcesPath(resourcesDirectory,name);
        if(FileOperations.trySaveMultipartFile(filePath,multipartFile)){
            return Optional.of(name);
        } else{
            return Optional.empty();
        }
    }

    private String generateFileName(MultipartFile multipartFile){
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        return nameGenerator.generateFileName(extension);
    }

    @Override
    public void deleteImage(String name){
        Path filePath = pathCreator.createResourcesPath(resourcesDirectory,name);
        FileOperations.tryDeleteFile(filePath);
    }
}
