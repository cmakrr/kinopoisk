package com.example.kinopoisk.services;

import com.example.kinopoisk.models.entities.EntityThatCanBeLiked;
import com.example.kinopoisk.models.entities.Like;
import com.example.kinopoisk.repositories.EntityThatCanBeLikedRepository;
import com.example.kinopoisk.repositories.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final EntityThatCanBeLikedRepository entityRepository;
    private final UserService userService;

    public void addLike(Long entityId){
        Optional<EntityThatCanBeLiked> entity = entityRepository.findById(entityId);
        entity.ifPresent(value->{
            Like like = new Like();
            like.setEntity(value);
            like.setUser(userService.receiveCurrentUser());
            likeRepository.save(like);
        });
    }

    @Transactional
    public void deleteLikeFromCurrentUser(Long entityId) {
        likeRepository.deleteByEntityIdAndUserId(entityId, userService.receiveCurrentUserId());
    }

    public boolean hasLikeFromCurrentUser(EntityThatCanBeLiked entity) {
        Long userId = userService.receiveCurrentUserId();
        return entity.getLikes().stream().anyMatch(like -> like.getUser().getId().equals(userId));
    }
}
