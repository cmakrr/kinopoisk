package com.example.kinopoisk.model.entities.link;

import com.example.kinopoisk.model.entities.platform.Platform;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Link {
    @GeneratedValue
    @Id
    private Long id;
    private String url;
    @ManyToOne
    private Platform platform;
}
