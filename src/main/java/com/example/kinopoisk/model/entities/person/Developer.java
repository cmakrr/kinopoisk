package com.example.kinopoisk.model.entities.person;

import com.example.kinopoisk.model.entities.organisation.Organisation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Developer extends Person{
    private int experienceInYears;
    private String department;
    private String title;
    @ManyToOne
    private Organisation organisation;
}
