package com.example.kinopoisk.services;

import com.example.kinopoisk.logic.dtoConverters.ReviewToDTOConverter;
import com.example.kinopoisk.models.dtos.ReviewDTO;
import com.example.kinopoisk.models.entities.Review;
import com.example.kinopoisk.models.entities.Show;
import com.example.kinopoisk.repositories.ReviewRepository;
import com.example.kinopoisk.repositories.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record ReviewService(ReviewRepository reviewRepository,
                            ShowRepository showRepository,
                            ReviewToDTOConverter reviewToDTOConverter,
                            UserService userService) {
    public void saveNewReview(Review review, Long showId){
        setShowToReview(review,showId);
        setCurrentUserToReview(review);
        reviewRepository.save(review);
    }

    private void setShowToReview(Review review, Long showId){
        Optional<Show> show = showRepository.findById(showId);
        show.ifPresent(review::setShow);
    }

    private void setCurrentUserToReview(Review review) {
        review.setUser(userService.receiveCurrentUser());
    }

    public void updateReview(Review review){
        reviewRepository.save(review);
    }

    public Optional<Review> findById(Long id){
        return reviewRepository.findById(id);
    }

    public boolean isCurrentUserAuthorOfReviewById(Long reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        return review.isPresent() && isCurrentUserAuthorOfReview(review.get());
    }

    public boolean isCurrentUserAuthorOfReview(Review review){
        Long loggedUserId = userService.receiveCurrentUserId();
        return review.getUser().getId().equals(loggedUserId);
    }

    public Optional<ReviewDTO> findDTOById(Long id){
        Optional<Review> review  = reviewRepository.findById(id);
        if(review.isPresent()){
            ReviewDTO dto = reviewToDTOConverter.convertToDTO(review.get());
            return Optional.of(dto);
        } else{
            return Optional.empty();
        }
    }

    public void deleteById(Long id){
        reviewRepository.deleteById(id);
    }

    public List<ReviewDTO> convertReviewsToDTOs(List<Review> reviews){
        return reviews.stream()
                .map(reviewToDTOConverter::convertToDTO)
                .toList();
    }
}
