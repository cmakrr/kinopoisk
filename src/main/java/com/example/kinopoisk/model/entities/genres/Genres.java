package com.example.kinopoisk.model.entities.genres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Genres {
    @GeneratedValue
    @Id
    private Long id;
    private String genre;
}
