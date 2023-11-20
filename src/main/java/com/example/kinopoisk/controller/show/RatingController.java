package com.example.kinopoisk.controller.show;

import com.example.kinopoisk.logic.auxiliaryClasses.AuxiliaryMethods;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.service.rating.interfaces.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping("/show/{showId}/set_rating")
    public String setRating(@PathVariable Long showId, @Valid Rating rating, BindingResult result){
        if(!result.hasErrors()){
            ratingService.saveRating(rating,showId);
        }
        return String.format("redirect:/show/%d",showId);
    }

    @PostMapping("/rating/{id}/delete")
    public String deleteRating(HttpServletRequest servletRequest, @PathVariable Long id){
        ratingService.deleteRating(id);
        return AuxiliaryMethods.createRedirectionToPreviousPage(servletRequest);
    }
}
