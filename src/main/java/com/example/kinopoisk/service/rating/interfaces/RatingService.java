package com.example.kinopoisk.service.rating.interfaces;

import com.example.kinopoisk.model.entities.rating.Rating;


public interface RatingService {

    void saveRating(Rating rating, Long showId);

    void deleteRating(Long id);
}
