package com.example.kinopoisk.model.entities.person;

import com.example.kinopoisk.model.entities.organisation.Organisation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Director extends Person {
    private int yearsOfService;
    private String department;
    private String biography;
    @ManyToMany
    private List<Organisation> organisations;
}
