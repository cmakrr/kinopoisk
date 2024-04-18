package com.example.kinopoisk.model.entities.show;

import com.example.kinopoisk.model.entities.person.Actor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends Product {
    @ManyToMany
    private List<Actor> actorList;
    private int lengthInMinutes;
    private int budget;
}
