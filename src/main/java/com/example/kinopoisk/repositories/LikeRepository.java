package com.example.kinopoisk.repositories;

import com.example.kinopoisk.models.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    void deleteByEntityIdAndUserId(Long entityId, Long userId);
}
