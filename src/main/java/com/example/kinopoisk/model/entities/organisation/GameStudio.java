package com.example.kinopoisk.model.entities.organisation;

import com.example.kinopoisk.model.entities.genres.Genres;
import com.example.kinopoisk.model.entities.person.Developer;
import com.example.kinopoisk.model.entities.show.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class GameStudio extends Organisation {
    @OneToMany
    private List<Game> games;
    @ManyToMany
    private List<Developer> developers;
    @ManyToMany
    private List<Genres> genres;
}
