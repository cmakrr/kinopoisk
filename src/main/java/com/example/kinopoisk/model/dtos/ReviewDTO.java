package com.example.kinopoisk.model.dtos;

import com.example.kinopoisk.model.entities.show.Product;
import com.example.kinopoisk.model.entities.user.User;
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
    private Product product;
    private User user;
    private boolean isLikedByCurrentUser;
    private Long likesCount;
}
