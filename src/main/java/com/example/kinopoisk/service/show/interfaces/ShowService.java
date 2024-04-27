package com.example.kinopoisk.service.show.interfaces;

import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.show.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ShowService {

    void save(Product product);

    void setImages(Product product, MultipartFile[] images);

    Optional<Product> findById(Long id);

    List<ReviewDTO> receiveShowReviewsDTO(Product product);

    Optional<Rating> receiveRatingFromCurrentUser(Product product);

    Optional<Product> findByName(String name);

    List<Product> findAll();

    List<Product> findByNameContaining(String name);
}
