package com.example.kinopoisk.service.rating.implementations;

import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.show.Product;
import com.example.kinopoisk.repository.product.ProductBaseRepository;
import com.example.kinopoisk.repository.rating.RatingRepository;
import com.example.kinopoisk.service.rating.interfaces.RatingService;
import com.example.kinopoisk.service.user.implementations.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomRatingService implements RatingService {

    private final RatingRepository ratingRepository;
    private final ProductBaseRepository productRepository;
    private final CustomUserService userService;

    @Override
    public void saveRating(Rating rating,Long showId){
        setIdIfRatingAlreadyExists(rating,showId);
        addShowToRating(rating,showId);
        addCurrentUserAsRatingAuthor(rating);
        ratingRepository.save(rating);
    }

    private void setIdIfRatingAlreadyExists(Rating rating,Long showId){
        Long userId = userService.receiveCurrentUserId();
        Optional<Rating> oldRating = ratingRepository.findByUserIdAndProductId(userId,showId);
        oldRating.ifPresent(value-> rating.setId(value.getId()));
    }

    private void addShowToRating(Rating rating,Long showId){
        Optional<Product> show = productRepository.findById(showId);
        show.ifPresent(rating::setProduct);
    }

    private void addCurrentUserAsRatingAuthor(Rating rating){
        rating.setUser(userService.receiveCurrentUser());
    }

    @Override
    public void deleteRating(Long id){
        ratingRepository.deleteById(id);
    }
}
