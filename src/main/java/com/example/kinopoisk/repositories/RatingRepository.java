package com.example.kinopoisk.repositories;

import com.example.kinopoisk.models.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    Optional<Rating> findByUserIdAndShowId(Long userId,Long showId);
}
