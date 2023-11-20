package com.example.kinopoisk.repository.likeableentity;

import com.example.kinopoisk.model.entities.likeableentity.EntityThatCanBeLiked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityThatCanBeLikedRepository extends JpaRepository<EntityThatCanBeLiked,Long> {
}
