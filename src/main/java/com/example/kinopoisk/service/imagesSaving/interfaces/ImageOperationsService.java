package com.example.kinopoisk.service.imagesSaving.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ImageOperationsService {

    List<String> saveImages(MultipartFile[] images);

    Optional<String> saveImage(MultipartFile multipartFile);

    void deleteImage(String name);
}
