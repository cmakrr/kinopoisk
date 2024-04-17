package com.example.kinopoisk.service.product.interfaces;

import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.review.Review;
import com.example.kinopoisk.model.entities.show.Product;

import java.util.List;

public interface ProductService {

    Product findById(Long id);

    List<ReviewDTO> receiveShowReviewsDTO(Product product);

    boolean isLikedByCurrentUser(Product product);

    Review receiveReviewFromCurrentUser(Product product);

    Product findByName(String name);
}
