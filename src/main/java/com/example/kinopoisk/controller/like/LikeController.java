package com.example.kinopoisk.controller.like;

import com.example.kinopoisk.logic.auxiliaryClasses.AuxiliaryMethods;
import com.example.kinopoisk.service.like.interfaces.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{entityId}")
    public void like(@PathVariable Long entityId){
        likeService.addLike(entityId);
    }

    @PostMapping("/delete/{entityId}")
    public void deleteLike(HttpServletRequest servletRequest,@PathVariable Long entityId){
        likeService.deleteLikeFromCurrentUser(entityId);
    }
}
