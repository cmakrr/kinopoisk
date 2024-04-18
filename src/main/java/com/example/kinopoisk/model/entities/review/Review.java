package com.example.kinopoisk.model.entities.review;

import com.example.kinopoisk.model.entities.show.Show;
import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.model.entities.likeableentity.EntityThatCanBeLiked;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "reviews")
@Getter
@Setter
public class Review extends EntityThatCanBeLiked {
    private String text;
    private Boolean isShowRecommended = false;
    @CreationTimestamp
    private Date date;
    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;
    @ManyToOne
    @JoinColumn
    private User user;
}
