package com.example.kinopoisk.repository.rating;

import com.example.kinopoisk.model.entities.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    Optional<Rating> findByUserIdAndShowId(Long userId,Long showId);
}
