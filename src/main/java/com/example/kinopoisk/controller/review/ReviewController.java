package com.example.kinopoisk.controller.review;

import com.example.kinopoisk.model.dtos.ReviewDTO;
import com.example.kinopoisk.model.entities.review.Review;
import com.example.kinopoisk.repository.show.ShowRepository;
import com.example.kinopoisk.service.review.interfaces.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ShowRepository showRepository;

    @GetMapping("/create/{showId}")
    public String create(@PathVariable Long showId, Model model){
        if(showRepository.existsShowById(showId)){
            model.addAttribute("showId",showId);
            model.addAttribute("review",new Review());
            return "review/create";
        } else{
            return "error/404";
        }
    }

    @PostMapping("/create/{showId}")
    public String createPost(@PathVariable Long showId, @Valid Review review, BindingResult result,Model model){
        if(!result.hasErrors()){
            reviewService.saveNewReview(review,showId);
            return String.format("redirect:/show/%d",showId);
        } else{
            model.addAttribute("showId",showId);
            model.addAttribute("review",review);
            return "review/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model){
        if(reviewService.isCurrentUserAuthorOfReviewById(id)){
            Review review = reviewService.findById(id).get();
            model.addAttribute("review",review);
            return "review/edit";
        } else{
            return "error/404";
        }
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable Long id,@Valid Review review, BindingResult result){
        if(!result.hasErrors()){
            reviewService.updateReview(review);
            return String.format("redirect:/review/%d",id);
        } else{
            return String.format("redirect:/review/edit/%d",id);
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        if(reviewService.isCurrentUserAuthorOfReviewById(id)){
            reviewService.deleteById(id);
            return "redirect:/profile/my_profile";
        } else{
            return String.format("redirect:/review/%d",id);
        }
    }

    @GetMapping("/{id}")
    public String review(@PathVariable Long id,Model model){
        Optional<ReviewDTO> review = reviewService.findDTOById(id);
        return review.map(value->{
            model.addAttribute("review",value);
            model.addAttribute("isOwner",reviewService.isCurrentUserAuthorOfReviewById(id));
            return "review/review";
        }).orElse("error/404");
    }
}