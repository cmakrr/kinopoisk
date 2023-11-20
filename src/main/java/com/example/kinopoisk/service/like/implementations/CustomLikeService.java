package com.example.kinopoisk.service.like.implementations;

import com.example.kinopoisk.model.entities.likeableentity.EntityThatCanBeLiked;
import com.example.kinopoisk.model.entities.like.Like;
import com.example.kinopoisk.repository.likeableentity.EntityThatCanBeLikedRepository;
import com.example.kinopoisk.repository.like.LikeRepository;
import com.example.kinopoisk.service.like.interfaces.LikeService;
import com.example.kinopoisk.service.user.implementations.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomLikeService implements LikeService {

    private final LikeRepository likeRepository;
    private final EntityThatCanBeLikedRepository entityRepository;
    private final CustomUserService userService;

    @Override
    public void addLike(Long entityId){
        Optional<EntityThatCanBeLiked> entity = entityRepository.findById(entityId);
        entity.ifPresent(value->{
            Like like = new Like();
            like.setEntity(value);
            like.setUser(userService.receiveCurrentUser());
            likeRepository.save(like);
        });
    }

    @Override
    @Transactional
    public void deleteLikeFromCurrentUser(Long entityId) {
        likeRepository.deleteByEntityIdAndUserId(entityId, userService.receiveCurrentUserId());
    }

    @Override
    public boolean hasLikeFromCurrentUser(EntityThatCanBeLiked entity) {
        Long userId = userService.receiveCurrentUserId();
        return entity.getLikes().stream().anyMatch(like -> like.getUser().getId().equals(userId));
    }
}
