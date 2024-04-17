package com.example.kinopoisk.model.entities.country;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Country {
    @GeneratedValue
    @Id
    private Long id;
    private String country;
}
