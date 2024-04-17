package com.example.kinopoisk.model.entities.person;

import com.example.kinopoisk.model.entities.organisation.Organisation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Author extends Person{
    @ManyToOne
    private Organisation organisation;
    private String biography;
}
