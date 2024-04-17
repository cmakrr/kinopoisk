package com.example.kinopoisk.model.entities.show;

import com.example.kinopoisk.model.entities.person.Developer;
import com.example.kinopoisk.model.entities.person.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Game extends Product{
    @OneToMany
    private List<Developer> developers;
    private int approximateDuration;
}
