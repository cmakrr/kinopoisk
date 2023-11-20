package com.example.kinopoisk.controller.show;

import com.example.kinopoisk.logic.auxiliaryClasses.AuxiliaryMethods;
import com.example.kinopoisk.model.entities.rating.Rating;
import com.example.kinopoisk.model.entities.review.Review;
import com.example.kinopoisk.model.entities.show.Show;
import com.example.kinopoisk.service.show.interfaces.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/create")
    public String createShow(Model model){
        Show show = new Show();
        model.addAttribute("show",show);
        return "show/create";
    }

    @PostMapping("/create")
    public String createShowPost(Model model, @Valid Show show, BindingResult result,@ModelAttribute("pictures") MultipartFile[] pictures){
        if(!result.hasErrors()){
            showService.setImages(show,pictures);
            showService.save(show);
        }
        model.addAttribute("show",show);
        return "show/create";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Long id){
        Optional<Show> show = showService.findById(id);
        return show.map(value->{
            Optional<Rating> rating = showService.receiveRatingFromCurrentUser(value);
            model.addAttribute("rating", rating.orElse(new Rating()));
            model.addAttribute("review",new Review());
            model.addAttribute("reviews",showService.receiveShowReviewsDTO(value));
            model.addAttribute("show",value);
            return "show/show";
        }).orElse("error/404");
    }

    @PostMapping("/find")
    public String findByName(HttpServletRequest request, @RequestParam String showName, Model model){
        Optional<Show> show = showService.findByName(showName);
        if(show.isPresent()){
            return String.format("redirect:/show/%d",show.get().getId());
        } else{
            model.addAttribute("searchMessage","not found");
            return AuxiliaryMethods.createRedirectionToPreviousPage(request);
        }
    }
}
