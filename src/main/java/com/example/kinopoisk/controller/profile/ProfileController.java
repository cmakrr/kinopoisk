package com.example.kinopoisk.controller.profile;

import com.example.kinopoisk.model.dtos.UserDTO;
import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.service.review.interfaces.ReviewService;
import com.example.kinopoisk.service.user.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ReviewService reviewService;

    @GetMapping("/my_profile")
    public String myProfile(Model model){
        Long currentUserId = userService.receiveCurrentUserId();
        return profileById(currentUserId,model);
    }

    @GetMapping("/{id}")
    public String profileById(@PathVariable Long id, Model model){
        Optional<UserDTO> userDTO = userService.findDTOById(id);
        return userDTO.map(user->{
            model.addAttribute("user",user);
            model.addAttribute("isOwner",userService.isCurrentUser(id));
            return "profile/profile";
                })
                .orElse("error/404");
    }

    @GetMapping("/edit/{id}")
    public String editProfileById(@PathVariable Long id,Model model){
        if(userService.isCurrentUser(id)){
            Optional<UserDTO> userDTO = userService.findDTOById(id);
            if(userDTO.isPresent()){
                model.addAttribute("user",userDTO.get());
                return "profile/profileEdit";
            }
        }
        return "error/404";
    }

    @PostMapping("/edit/{id}")
    public String editProfileByIdPost(Model model,@Valid UserDTO user, BindingResult result, @ModelAttribute("avatar")MultipartFile avatar){
        if(!result.hasErrors()){
            if(userService.isNewUsernameAppropriate(user)){
                userService.setAvatar(user,avatar);
                userService.updateUserByDTO(user);
                model.addAttribute("user",user);
                return "profile/profileEdit";
            } else{
                result.rejectValue("username","username.error","username is not unique");
            }
        }
        return String.format("redirect:/profile/edit/%d",user.getId());
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id){
        if(userService.isCurrentUser(id)) {
            userService.deleteUser(id);
            return "redirect:/login";
        } else{
            return String.format("redirect:/profile/%d",id);
        }
    }

    @GetMapping("/{id}/reviews_list")
    public String reviewsList(@PathVariable Long id,Model model){
        Optional<User> user= userService.findUserById(id);
        return user.map(value->{
            model.addAttribute("reviews",reviewService.convertReviewsToDTOs(value.getReviews()));
            model.addAttribute("isOwner",userService.isCurrentUser(id));
            return "profile/reviews_list";
        }).orElse("error/404");
    }
}
