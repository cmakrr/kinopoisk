package com.example.kinopoisk.service.like.interfaces;

import com.example.kinopoisk.model.entities.likeableentity.EntityThatCanBeLiked;

public interface LikeService {

    void addLike(Long entityId);

    void deleteLikeFromCurrentUser(Long entityId);

    boolean hasLikeFromCurrentUser(EntityThatCanBeLiked entity);
}
