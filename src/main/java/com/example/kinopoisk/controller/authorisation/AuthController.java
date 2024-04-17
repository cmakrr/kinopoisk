package com.example.kinopoisk.controller.authorisation;

import com.example.kinopoisk.exception.AuthException;
import com.example.kinopoisk.model.dtos.LoginDto;
import com.example.kinopoisk.model.dtos.RegisterDto;
import com.example.kinopoisk.model.dtos.UserDTO;
import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.repository.user.UserRepository;
import com.example.kinopoisk.service.user.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Logged in";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterDto signUpDto){

        if(!userService.isUsernameUnique(signUpDto.getUsername())){
           throw  new AuthException();
        }

        User user = new User();
        user.setUsername(signUpDto.getUsername());
        user.setPassword(signUpDto.getPassword());
        userService.saveNewUser(user);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signUpDto.getUsername(), signUpDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "registered";

    }

}
