package com.example.kinopoisk.service.review.interfaces;

import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.review.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    void saveNewReview(Review review, Long showId);

    void updateReview(Review review);

    Optional<Review> findById(Long id);

    boolean isCurrentUserAuthorOfReviewById(Long reviewId);

    boolean isCurrentUserAuthorOfReview(Review review);

    Optional<ReviewDTO> findDTOById(Long id);

    void deleteById(Long id);

    List<ReviewDTO> convertReviewsToDTOs(List<Review> reviews);
}
