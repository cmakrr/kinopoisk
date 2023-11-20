package com.example.kinopoisk.model.entities.like;

import com.example.kinopoisk.model.entities.user.User;
import com.example.kinopoisk.model.entities.likeableentity.EntityThatCanBeLiked;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
public class Like {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityThatCanBeLiked entity;
    @ManyToOne
    @JoinColumn
    private User user;
}
