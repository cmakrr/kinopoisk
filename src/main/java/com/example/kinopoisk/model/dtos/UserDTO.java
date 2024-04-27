package com.example.kinopoisk.model.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO{
    private Long id;
    @NotBlank
    private String username;
    private String avatarName;
    private int likesCount;
    private int reviewsCount;
    private String role;
}
