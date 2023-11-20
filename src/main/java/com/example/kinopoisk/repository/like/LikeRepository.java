package com.example.kinopoisk.repository.like;

import com.example.kinopoisk.model.entities.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    void deleteByEntityIdAndUserId(Long entityId, Long userId);
}
