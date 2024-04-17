package com.example.kinopoisk.model.entities.organisation;

import com.example.kinopoisk.model.entities.genres.Genres;
import com.example.kinopoisk.model.entities.person.Actor;
import com.example.kinopoisk.model.entities.show.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class MovieStudio extends Organisation{
    @OneToMany
    private List<Movie> movies;
    @OneToMany
    private List<Actor> actors;
    @ManyToMany
    private List<Genres> genres;
}
