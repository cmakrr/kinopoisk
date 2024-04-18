package com.example.kinopoisk.model.entities.music;

import com.example.kinopoisk.model.entities.organisation.Publisher;
import com.example.kinopoisk.model.entities.person.Author;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Music {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    @ManyToOne
    private Publisher publisher;
    @ManyToOne
    private Author author;
}
