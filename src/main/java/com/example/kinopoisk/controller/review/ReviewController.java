package com.example.kinopoisk.controller.review;

import com.example.kinopoisk.model.entities.review.Review;
import com.example.kinopoisk.repository.product.ProductBaseRepository;
import com.example.kinopoisk.service.review.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ProductBaseRepository productRepository;

    @PostMapping("/create/{showId}")
    public void createPost(@PathVariable Long showId, @RequestBody @Valid Review review){
        reviewService.saveNewReview(review,showId);
    }

    @PostMapping("/edit/{id}")
    public void editPost(@PathVariable Long id,@RequestBody @Valid Review review){
        reviewService.updateReview(review);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        if(reviewService.isCurrentUserAuthorOfReviewById(id)){
            reviewService.deleteById(id);
        }
    }
}