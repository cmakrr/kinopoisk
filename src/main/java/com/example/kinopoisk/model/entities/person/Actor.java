package com.example.kinopoisk.model.entities.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Actor extends Person {
    private String title;
}
