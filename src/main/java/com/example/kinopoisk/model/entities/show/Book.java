package com.example.kinopoisk.model.entities.show;

import com.example.kinopoisk.model.entities.person.Author;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Book extends Product{
    @ManyToOne
    private Author author;
    private int pagesCount;
    private String originalLanguage;
}
