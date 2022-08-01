package com.example.kinopoisk.models.dtos;

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
}
