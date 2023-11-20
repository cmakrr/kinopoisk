package com.example.kinopoisk.controller.like;

import com.example.kinopoisk.logic.auxiliaryClasses.AuxiliaryMethods;
import com.example.kinopoisk.service.like.interfaces.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{entityId}")
    public String like(HttpServletRequest servletRequest, @PathVariable Long entityId){
        likeService.addLike(entityId);
        return AuxiliaryMethods.createRedirectionToPreviousPage(servletRequest);
    }

    @PostMapping("/delete/{entityId}")
    public String deleteLike(HttpServletRequest servletRequest,@PathVariable Long entityId){
        likeService.deleteLikeFromCurrentUser(entityId);
        return AuxiliaryMethods.createRedirectionToPreviousPage(servletRequest);
    }
}
