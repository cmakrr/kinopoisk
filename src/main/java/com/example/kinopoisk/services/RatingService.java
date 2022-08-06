package com.example.kinopoisk.services;

import com.example.kinopoisk.models.entities.Rating;
import com.example.kinopoisk.models.entities.Show;
import com.example.kinopoisk.repositories.RatingRepository;
import com.example.kinopoisk.repositories.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record RatingService(RatingRepository ratingRepository,
                            ShowRepository showRepository,
                            UserService userService) {
    public void saveRating(Rating rating,Long showId){
        setIdIfRatingAlreadyExists(rating,showId);
        addShowToRating(rating,showId);
        addCurrentUserAsRatingAuthor(rating);
        ratingRepository.save(rating);
    }

    private void setIdIfRatingAlreadyExists(Rating rating,Long showId){
        Long userId = userService.receiveCurrentUserId();
        Optional<Rating> oldRating = ratingRepository.findByUserIdAndShowId(userId,showId);
        oldRating.ifPresent(value-> rating.setId(value.getId()));
    }

    private void addShowToRating(Rating rating,Long showId){
        Optional<Show> show = showRepository.findById(showId);
        show.ifPresent(rating::setShow);
    }

    private void addCurrentUserAsRatingAuthor(Rating rating){
        rating.setUser(userService.receiveCurrentUser());
    }

    public void deleteRating(Long id){
        ratingRepository.deleteById(id);
    }
}
