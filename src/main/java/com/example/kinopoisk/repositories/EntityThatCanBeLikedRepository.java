package com.example.kinopoisk.repositories;

import com.example.kinopoisk.models.entities.EntityThatCanBeLiked;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityThatCanBeLikedRepository extends JpaRepository<EntityThatCanBeLiked,Long> {
}
