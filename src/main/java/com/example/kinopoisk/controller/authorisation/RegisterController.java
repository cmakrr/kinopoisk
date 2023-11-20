package com.example.kinopoisk.controller.authorisation;

import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.service.user.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "authorisation/registration";
    }

    @PostMapping("")
    public String registerPost(@Valid User user, BindingResult result){
        if(!result.hasErrors()){
            if(userService.isUsernameUnique(user.getUsername())){
                userService.saveNewUser(user);
                return "redirect:/login";
            } else{
                result.rejectValue("username","username.error","username is already taken");
            }
        }
        return "authorisation/registration";
    }
}
