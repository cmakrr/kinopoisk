package com.example.kinopoisk.models.dtos;

import com.example.kinopoisk.models.entities.Show;
import com.example.kinopoisk.models.entities.User;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private String text;
    private boolean isRecommended;
    private Date date;
    private Show show;
    private User user;
    private boolean isLikedByCurrentUser;
    private Long likesCount;
}
